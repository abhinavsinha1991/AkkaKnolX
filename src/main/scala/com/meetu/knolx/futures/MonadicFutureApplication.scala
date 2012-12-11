package com.meetu.knolx.futures

import akka.dispatch._
import akka.util.duration._
import akka.actor.ActorSystem

object MonadicFutureApplication extends App {
  implicit val system = ActorSystem("future")

  val f1 = Future { "Hello" + "World" }
  val f2 = f1 map { x => x.length }
  
  val result = Await.result(f2, 1 second)
  println(result)
}