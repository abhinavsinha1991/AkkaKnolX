package com.meetu.knolx

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem

object SupervisionApp extends App {
	val system = ActorSystem("supervisionApp")
	val parent = system.actorOf(Props[ParentActor], "parent") ! "init"
}

class ParentActor extends Actor {
  def receive = {
    case msg =>
      println("In Parent")
      context.actorOf(Props[ChildActor], "child") ! ""
  }
}

class ChildActor extends Actor {
  def receive = {
    case msg => println("In Child")
  }
}