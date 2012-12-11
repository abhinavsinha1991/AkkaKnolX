package com.meetu.knolx.futures

import akka.dispatch._
import akka.util.duration._
import akka.actor.ActorSystem

object ProfileMonadicFutureApplication extends App {
  implicit val system = ActorSystem("future")
  
  nonBlockingComposition map {
    case profile => println(profile)
  }

  def nonBlockingComposition: Future[Profile] = {
    val futureName = name()
    val futureScore = score()
    val futureFriends = friends()

    for {
      nameResult <- futureName
      scoreResult <- futureScore
      friendsResult <- futureFriends
    } yield {
      Profile(nameResult, scoreResult, friendsResult)
    }
  }

  def blockingAndVerbose: Profile = {
    val futureName = name()
    val futureScore = score()
    val futureFriends = friends()

    val nameResult = Await.result(futureName, 10 seconds)
    val scoreResult = Await.result(futureScore, 10 seconds)
    val friendsResult = Await.result(futureFriends, 10 seconds)

    Profile(nameResult, scoreResult, friendsResult)
  }

  def name(): Future[String] = Future { "david" }
  def score(): Future[Double] = Future { 50.0 }
  def friends(): Future[List[Int]] = Future { List(1, 2, 3) }

}

case class Profile(name: String, score: Double, friends: List[Int])