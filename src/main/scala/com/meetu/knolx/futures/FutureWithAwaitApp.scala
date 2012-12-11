package com.meetu.knolx.futures

import akka.actor._
import akka.pattern.ask
import akka.util.duration._
import akka.util.Timeout
import akka.dispatch.Await

object FutureWithAwaitApp extends App {
  implicit val timeout = Timeout(50000 milliseconds)
  val system = ActorSystem("future")
  val echoActor = system.actorOf(Props[EchoActor])
  val future = echoActor ? "Hello World"
  val result = Await.result(future, timeout.duration).asInstanceOf[String]
  println(result)
}

class EchoActor extends Actor {
  def receive = {
    case msg => sender ! msg
  }
}