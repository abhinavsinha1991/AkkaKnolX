package com.meetu.knolx

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem

object ActorPathApp extends App {
	val system = ActorSystem("actorPathApp")
	val parent = system.actorOf(Props[ParentActor], "parent") ! "init"
	system.actorFor("akka://actorPathApp/user/parent/child") ! ""
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