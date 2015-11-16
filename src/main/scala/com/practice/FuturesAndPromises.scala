package com.practice

import concurrent.Future
import concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random

object FuturesAndPromises extends App {

  // First exemple "Hello World"
  val f: Future[String] = Future {
    "Hello world!!"
  }

  println("Exemple Future ...")
  Thread.sleep(2000)
  f.onComplete {
    case Success(value) => println(value)
    case Failure(error) => println(error)
  }
  println("Exemple Future end")
}
