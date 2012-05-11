package com.meetu.knolx

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object ActorSelectionApp extends App {
  val system = ActorSystem("actorSelectionApp")
  val parent = system.actorOf(Props[WorkerActor], "parent")
  parent ! CreateChild("child1")
  parent ! CreateChild("child2")
  parent ! CreateChild("child3")
  println(parent.path)
  val actorSelection = system.actorSelection("akka://actorSelectionApp/user/parent/*")
}

class WorkerActor extends Actor {
  def receive = {
    case msg: String => println("Message received " + msg)
    case createChild: CreateChild => 
      context.actorOf(Props[WorkerActor], createChild.name)
      println("child created")
  }
}

case class CreateChild(name: String)