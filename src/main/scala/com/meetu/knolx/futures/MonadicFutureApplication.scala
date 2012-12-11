package com.meetu.knolx.futures

import java.util.concurrent.Executors

import akka.dispatch._
import akka.util.duration._

object MonadicFutureApplication extends App {
  val execService = Executors.newCachedThreadPool()
  implicit val execContext = ExecutionContext.fromExecutorService(execService)

  val f1 = Future { "Hello" + "World" }
  val f2 = f1 map { x => x.length }
  
  val result = Await.result(f2, 1 second)

}