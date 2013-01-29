package com.meetu.knolx.futureandpromise

import akka.actor.ActorSystem
import akka.dispatch.Future
import akka.dispatch.Promise

/**
 * Promise is hardly used. But good to know, how they work
 */
object PromiseApp extends App {
  implicit val system = ActorSystem("future")
  
  // create a promise
  val promise = Promise[String]()
  
  // get an associated future from that promise
  val future = promise.future
  
  // successfully fulfill that promise
  promise.success("promises are to be kept")
  
  // Extract the value from the Future
  println(future.value)

}