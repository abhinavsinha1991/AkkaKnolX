package com.meetu.knolx.futures

import java.util.concurrent.Executors

import akka.dispatch._

object FutureWithoutActorApplication extends App {
  val execService = Executors.newCachedThreadPool()
  implicit val execContext = ExecutionContext.fromExecutorService(execService)
  val futureFib = Future {
    println(fib(20))
  }
  println("Thread execution continues...")

  def fib(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib(n - 1) + fib(n - 2)
  }
}