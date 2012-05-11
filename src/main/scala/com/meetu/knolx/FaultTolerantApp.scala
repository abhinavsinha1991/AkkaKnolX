package com.meetu.knolx

import akka.actor.Actor

object FaultTolerantApp extends App {

}

class FaultTolerantService extends Actor {
  def receive = {
    case msg => println(msg)
  }

  override def preRestart(reason: Throwable, message: Option[Any]) = {

  }

  override def postRestart(reason: Throwable) = {

  }
}