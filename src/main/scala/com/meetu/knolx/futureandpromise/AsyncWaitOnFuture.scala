package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future

object AsyncWaitOnFuture extends App {
  implicit val system = ActorSystem("future")

  // Future[String] 
  val future = Future { "Hello " + " World" }

  // Futures are monadic so we can transform from Future[String] to Future[Int]
  val anotherFuture = future map {
    aString => aString.length
  }

  // there are callbacks for example onSuccess.
  anotherFuture onSuccess {
    case number => println("String length is " + number)
  }

}