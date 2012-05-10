package com.meetu.knolx

import akka.actor.{ Actor, ActorRef, ActorSystem, PoisonPill, Props }
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.duration.intToDurationInt
import akka.util.Timeout

object AskPatternApp extends App {
  implicit val timeout = Timeout(50000 milliseconds)
  val system = ActorSystem("askPattern")
  val longWorkingActor = system.actorOf(Props[LongWorkingActor])
  val future = (system.actorOf(Props[LongWorkingActor]) ? 10).mapTo[Int]
  future map {
    case result: Int => println(result)
    case _ => println("error")
  }
}

class LongWorkingActor extends Actor {
  def receive = {
    case number: Int =>
      sender ! fibonacci(number)
  }

  private def fibonacci(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fibonacci(n - 1) + fibonacci(n - 2)
  }
}