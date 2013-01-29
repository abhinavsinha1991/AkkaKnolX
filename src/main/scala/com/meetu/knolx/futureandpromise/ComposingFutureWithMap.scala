package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future

/**
 * We want to call identity function twice and calculate their sum. Identity function sleeps for three seconds
 */
object ComposingFutureWithMap extends App {
  implicit val system = ActorSystem("future")
  val startTime = System.currentTimeMillis
  // creating two futures
  val future1 = Future { identity(1) }
  val future2 = Future { identity(2) }

  // Way to do things parallel is to compose another Future out of future1 and future2
  // lets just use map method

  // bad!! we get Future[Future[Int]] bad for conciseness
  val composedFuture = future1 map {
    result1 =>
      future2 map {
        result2 => result1 + result2
      }
  }

  // so we use flatMap with map method :)
  // we get Future[Int]
  val finalFuture = future1 flatMap {
    result1 =>
      future2 map {
        result2 => result1 + result2
      }
  }

  finalFuture onSuccess {
    case sum =>
      val elapsedTime = (System.currentTimeMillis - startTime) / 1000.0
      println("sum is " + sum + " calculated in " + elapsedTime + " seconds")
  }

  def identity(number: Int): Int = {
    Thread.sleep(3000)
    number
  }
}