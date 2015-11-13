package com.practice

object OptionAndTry extends App {

  // Option
  val persons: List[PersonModel] = PersonModel.initPersons(4)
  val mayBePerson: Option[PersonModel] = PersonModel.findByName(persons, persons.head.name);
  println(mayBePerson)

  // pattern matching
  val personName: String = mayBePerson match {
    case Some(p) => p.name
    case None => "not a person !"
  }
  println(personName)


}
