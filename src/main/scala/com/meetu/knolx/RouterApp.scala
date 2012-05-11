package com.meetu.knolx

import akka.actor.{ Actor, ActorSystem, Props }
import akka.routing.{ RoundRobinRouter, Broadcast }

object RouterApp extends App {
  val system = ActorSystem("routerApp")
  val router = system.actorOf(Props[RouterWorkerActor].withRouter(RoundRobinRouter(nrOfInstances = 5)))
  router ! Broadcast("Hello")
}

class RouterWorkerActor extends Actor {
  def receive = {
    case msg => println(msg)
  }
}