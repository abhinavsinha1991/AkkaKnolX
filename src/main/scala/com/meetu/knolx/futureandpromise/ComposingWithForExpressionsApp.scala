package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future

object ComposingWithForExpressionsApp extends App {
  implicit val system = ActorSystem("future")
  val startTime = System.currentTimeMillis

  // creating three futures
  val future1 = Future { identity(1) }
  val future2 = Future { identity(2) }
  val future3 = Future { identity(3) }

  // composing using map and flatMqp
  // not concise at all :(
  val oneFinalFuture = future1 flatMap {
    result1 =>
      future2 flatMap {
        result2 =>
          future3 map {
            result3 => result1 + result2 + result3
          }
      }
  }

  // for expressions are just a sugar. They are really concise :)
  val finalFuture = for {
    result1 <- future1
    result2 <- future2
    result3 <- future3
  } yield result1 + result2 + result3

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