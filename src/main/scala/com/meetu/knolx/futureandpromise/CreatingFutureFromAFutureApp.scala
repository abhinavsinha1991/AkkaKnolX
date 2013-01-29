package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future

/**
 * We create a Future and then compose a new Future out of it.
 */
object CreatingFutureFromAFutureApp extends App {
  implicit val system = ActorSystem("future")

  // Future[String] 
  val future = Future { "Hello " + " World" }

  // Futures are monadic so we can transform from Future[String] to Future[Int]
  val anotherFuture = future map {
    aString => aString.length
  }

}