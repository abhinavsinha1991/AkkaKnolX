package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future
import akka.pattern.ask
import akka.util.duration._
import akka.util.Timeout
import akka.dispatch.Await

object SyncWaitOnFuture extends App {
  implicit val system = ActorSystem("future")
  implicit val timeout = Timeout(50000 milliseconds)

  // Future[String] 
  val future = Future { "Hello " + " World" }

  // Futures are monadic so we can transform from Future[String] to Future[Int]
  val anotherFuture = future map {
    aString => aString.length
  }

  // try to avoid this as much as possible
  val number = Await.result(anotherFuture, timeout.duration)
  println("String length is " + number)
}