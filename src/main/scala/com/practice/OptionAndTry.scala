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

}
