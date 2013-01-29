package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future

object SimplestFutureApp extends App {
  implicit val system = ActorSystem("future")
  val future = Future { "Hello " + " World" }
}