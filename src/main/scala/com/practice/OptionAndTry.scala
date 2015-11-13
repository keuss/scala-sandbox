package com.practice

import scala.util.Failure
import scala.util.Success


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


  // Try -------------------------
  val filename = "/etc/passwd"
  Utils.readTextFile(filename) match {
    case Success(lines) => lines.foreach(println)
    case Failure(f) => println(f)
  }

}
