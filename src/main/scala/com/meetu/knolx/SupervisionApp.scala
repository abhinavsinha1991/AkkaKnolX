package com.meetu.knolx

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem

object SupervisionApp extends App {
	val system = ActorSystem("supervisionApp")
	val parent = system.actorOf(Props[ParentActor], "parent") ! "init"
	system.actorFor("akka://supervisionApp/user/parent/child") ! ""
}

class ParentActor extends Actor {
  def receive = {
    case msg =>
      println(context.actorOf(Props[ChildActor], "child").path)
  }
}

class ChildActor extends Actor {
  def receive = {
    case msg => println("In Child")
  }
}