package com.practice

object ClassAndCaseClass extends App {

  // class -----------------------
  val p1 = new Person("Mike", 45)
  println(s"p1 : $p1")

  // case class ------------------
  // no new (thank to apply method) !
  // free getter !
  // immutable !
  // pattern matching !
  // toString equal et hashCode ready !
  // copy ready !
  val p2 = PersonModel("Mike", 45)
  println(s"p2 : $p2")
  println(s"p2 : ${p2.name}")
  //p2.name = "John";
  //println(s"p2 : ${p2.name}")

  // Collections -----------------
  val persons: List[PersonModel] = PersonModel.initPersons(4)
  println("Persons : " + persons)


}
