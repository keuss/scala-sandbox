package com.practice

import java.net.URL

import scala.io.Source
import scala.util.{Try, Failure, Success}


object OptionAndTry extends App {

  // Option ----------------------
  val persons: List[PersonModel] = PersonModel.initPersons(4)
  val mayBePerson: Option[PersonModel] = PersonModel.findByName(persons, persons.head.name);
  println(mayBePerson)

  // pattern matching
  val personName: String = mayBePerson match {
    case Some(p) => p.name
    case None => "not a person !"
  }
  println(personName)

  val personsWithContact = PersonModel.initPersonsWithContact
  println(PersonModel.findHasContact(personsWithContact))

  // sugar
  println(personsWithContact.head.contact.flatMap(c => c.telephone).getOrElse(""))
  println(personsWithContact.head.contact.flatMap(_.telephone).getOrElse(""))


  // Try -------------------------
  val filename = "/etc/passwd"
  Utils.readTextFile(filename) match {
    case Success(lines) => lines.foreach(println)
    case Failure(f) => println(f)
  }

  // optionToTry
  //val mayBePersonNone = None
  println(Utils.optionToTry(mayBePerson))
  Utils.optionToTry(mayBePerson) match {
    case Success(p) => println(p)
    case Failure(f) => println(f)
  }

  // for
  def parseURL(url: String): Try[URL] = Try(new URL(url))
  println(parseURL("http://danielwestheide.com").map(_.getProtocol))
  // results in Success("http")
  println(parseURL("garbage").map(_.getProtocol))
  // results in Failure(java.net.MalformedURLException: no protocol: garbage)

  /**
   * getURLContent
   */
  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseURL(url)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()

  // test getURLContent
  getURLContent("http://danielwestheide.com/foobar") match {
    case Success(lines) => lines.foreach(println)
    case Failure(ex) => println(s"Problem rendering URL content: ${ex.getMessage}")
  }


  // Other test ------------------
  // Some type aliases, just for getting more meaningful method signatures:
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  // dummy implementations of the individual steps:
  def grind(beans: CoffeeBeans): GroundCoffee = s"ground coffee of $beans"
  def heatWater(water: Water): Water = water.copy(temperature = 85)
  def frothMilk(milk: Milk): FrothedMilk = s"frothed $milk"
  def brew(coffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"
  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"
  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  // going through these steps sequentially:
  def prepareCappuccino(): Try[Cappuccino] = for {
    ground <- Try(grind("arabica beans"))
    water <- Try(heatWater(Water(25)))
    espresso <- Try(brew(ground, water))
    foam <- Try(frothMilk("milk"))
  } yield combine(espresso, foam)

  prepareCappuccino() match {
    case Success(c) => println(s"$c is ready !")
    case Failure(ex) => println(s"Problem prepareCappuccino: ${ex.getMessage}")
  }

  // See FuturesAndPromises ...

}
