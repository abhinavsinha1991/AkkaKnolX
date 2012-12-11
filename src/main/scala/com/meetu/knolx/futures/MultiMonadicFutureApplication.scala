package com.meetu.knolx.futures

import akka.dispatch._
import akka.util.duration._
import akka.actor.ActorSystem

object MultiMonadicFutureApplication extends App {
  implicit val system = ActorSystem("future")

  val f1 = Future { "Hello" + "World" }
  val f2 = Future { 3 }
  val f3 = f1 map { x =>
    f2 map { y =>
      x.length * y
    }
  }
  
  val f4 = f1 flatMap { x =>
    f2 map { y =>
      x.length * y
    }
  }

  val result = Await.result(f3, 1 second)
  val result1 = Await.result(f4, 1 second)
  println(result)
  println(result1)
}