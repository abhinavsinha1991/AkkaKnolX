package com.meetu.knolx.futures

import akka.actor._
import akka.pattern.ask
import akka.util.duration._
import akka.util.Timeout
import akka.dispatch.Await
import akka.dispatch.Future

object FutureDirectlyApp extends App {
  implicit val timeout = Timeout(50 seconds)
  implicit val system = ActorSystem("future")
  val future = Future {
    Thread.sleep(1000)
    "Hello" + "World"
  }
  val result = Await.result(future, 100 second)
  println(result)
  println("Thread completes...")
}