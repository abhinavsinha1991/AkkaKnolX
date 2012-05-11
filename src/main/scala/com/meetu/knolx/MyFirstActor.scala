package com.meetu.knolx

import akka.actor.{ Actor, ActorSystem, Props }

object MyFirstActorApp extends App {
  val system = ActorSystem("firstApp")
  val myFirstActor = system.actorOf(Props[MyFirstActor])
  myFirstActor ! "Hi"
}

class MyFirstActor extends Actor {
  def receive = {
    case msg => println("Hello from Akka Actor!!")
  }
}