package com.meetu.knolx.futureandpromise

object WithoutFutureApp extends App {
  val startTime = System.currentTimeMillis

  val result1 = identity(1)
  val result2 = identity(2)

  val sum = result1 + result2

  val elapsedTime = (System.currentTimeMillis - startTime) / 1000.0
  println("sum is " + sum + " calculated in " + elapsedTime + " seconds")

  def identity(number: Int): Int = {
    Thread.sleep(3000)
    number
  }

}