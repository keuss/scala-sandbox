package com.practice

import concurrent.Future
import concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Failure, Success, Random}
import scala.concurrent.duration._

object FuturesAndPromises extends App {

  // Futures & Promises
  // First exemple "Hello World" -
  val f: Future[String] = Future {
    "Hello world!!"
  }

  println("Exemple Promises ...")

  f.onComplete {
    case Success(value) => println(value)
    case Failure(error) => println(error)
  }
  println("Exemple Future end")
  Thread.sleep(2000)

  // exemple from http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html

  // Sequential code -------------

  // Some type aliases, just for getting more meaningful method signatures:
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String
  // dummy implementations of the individual steps:
  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"
  // some exceptions for things that might go wrong in the individual steps
  // (we'll need some of them later, use the others when experimenting
  // with the code):
  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  println("Working with Futures ...")
  // Working with Futures
  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("start grinding...")
    Thread.sleep(Random.nextInt(2000))
    if (beans == "baked beans") throw GrindingException("are you joking?")
    println("finished grinding...")
    s"ground coffee of $beans"
  }

  def heatWater(water: Water): Future[Water] = Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot!")
    water.copy(temperature = 85)
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged!")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }

  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }

  def prepareCappuccino(): Future[Cappuccino] = {
    // test with "baked beans"
    val groundCoffee = grind("arabica beans")
    val heatedWater = heatWater(Water(20))
    val frothedMilk = frothMilk("milk")
    for {
      ground <- groundCoffee
      water <- heatedWater
      foam <- frothedMilk
      espresso <- brew(ground, water)
    } yield combine(espresso, foam)
  }

  prepareCappuccino().onComplete {
    case Success(value) => println(s"$value IS READY !!!!!!!!")
    case Failure(error) => println(error)
  }

  Thread.sleep(10000)

}
