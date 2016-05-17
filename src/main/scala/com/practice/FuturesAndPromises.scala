package com.practice

import scala.concurrent.{Promise, Future}
import concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Failure, Success, Random}
import scala.concurrent.duration._

object FuturesAndPromises extends App {

  // Futures & Promises
  // see source code : https://github.com/scala/scala/blob/v2.11.8/src/library/scala/concurrent/Future.scala
  // and http://docs.scala-lang.org/overviews/core/futures.html
  // First exemple "Hello World"
  // call apply method on the Future companion object, that requires two arguments :
  // - The computation to be computed asynchronously is passed in as the body by-name parameter
  // - An ExecutionContext is something that can execute our future, and you can think of it as something like a thread pool
  val f: Future[String] = Future {
    "Hello world!!"
  }

  println("######## Exemple Future ...")

  f.onComplete {
    case Success(value) => println(value)
    case Failure(error) => println(error)
  }
  println("Exemple Future end")
  Thread.sleep(2000)

  // exemple from http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html

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

  // Sequential code ----------------------------
  // dummy implementations of the individual steps:
  def grindSequential(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"
  def heatWaterSequential(water: Water): Water = water.copy(temperature = 85)
  def frothMilkSequential(milk: Milk): FrothedMilk = s"frothed $milk"
  def brewSequential(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"

  def prepareCappuccinoSequential(): Try[Cappuccino] = for {
    ground <- Try(grindSequential("arabica beans"))
    water <- Try(heatWaterSequential(Water(25)))
    espresso <- Try(brewSequential(ground, water))
    foam <- Try(frothMilkSequential("milk"))
  } yield combine(espresso, foam)

  println(s"prepareCappuccinoSequential : ${prepareCappuccinoSequential()}")
  Thread.sleep(2000)

  // Working with Futures -----------------------
  println("Working with Futures ...")
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

  println("######## Exemple Promises ...")
  // A Promise instance is always linked to exactly one instance of Future.
  println(s"f is ${f}")

  case class TaxCut(value: Int)

  def redeemCampaignPledge(): Future[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(2000)
      p.success(TaxCut(20))
      println("We reduced the taxes! You must reelect us !!!")
    }
    p.future
  }

  val taxCutF: Future[TaxCut] = redeemCampaignPledge()
  println("Now that they're elected, let's see if they remember their promises...")
  taxCutF.onComplete {
    case Success(TaxCut(reduction)) =>
      println(s"A miracle! They really cut our taxes by $reduction percentage points!")
    case Failure(ex) =>
      println(s"They broke their promises! Again! Because of a ${ex.getMessage}")
  }

  // If you try this out multiple times, you will see that the order of the console output is not deterministic.
  // Eventually, the completion handler will be executed and run into the success case.

  Thread.sleep(10000)

  // Others Examples

  // Creates an already completed Future with the specified result (wrapping)
  Future.successful("Hello world!!").onComplete(r => println(r))

  val f1: Future[String] = Future {
    Thread.sleep(500)
    "Hello world 1"
  }

  val f2: Future[String] = Future {
    Thread.sleep(2000)
    "Hello world 2"
  }

  // Returns a new `Future` to the result of the first future in the list that is completed
  Future.firstCompletedOf(Seq(f1, f2)).map(firtValue => println(firtValue))
  Thread.sleep(5000)
  println("Test firstCompletedOf end")

  def computeFake(v: String) = {
    Thread.sleep(Random.nextInt(1000))
    if(v == "foo")
      Thread.sleep(3000)
    v + "compute"
  }

  // This is useful for performing a parallel map. For example, to apply a function to all items of a list in parallel
  Future.traverse(Seq("Yo", "Ya", "foo", "bar")) {
    x => Future {
      val cx = computeFake(x)
      println(cx)
      cx
    }
  }.map { resultList =>
    println("resultList size is " + resultList.size)
    resultList match {
      case resultList if resultList.size != 4 => println("ERROR !")
      case resultList => println("OK : " + resultList)
    }
  }
  println("Test traverse end")

  Thread.sleep(15000)
}
