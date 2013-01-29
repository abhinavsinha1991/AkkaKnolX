package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future

object ForExpressionBeAware extends App {
  implicit val system = ActorSystem("future")
  val startTime = System.currentTimeMillis

  val finalFuture = for {
    result1 <- Future { identity(1) }
    result2 <- Future { identity(2) }
    result3 <- Future { identity(3) }
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