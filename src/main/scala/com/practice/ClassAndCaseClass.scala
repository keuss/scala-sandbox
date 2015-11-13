package com.practice

object ClassAndCaseClass extends App {

  // class -----------------------
  val p1 = new Person("Mike", 45)
  println(s"p1 : $p1")

  // case class ------------------
  // no new !
  // free getter !
  // immutable !
  // pattern matching !
  val p2 = PersonModel("Mike", 45)
  println(s"p2 : $p2")
  println(s"p2 : ${p2.name}")
  //p2.name = "John";
  //println(s"p2 : ${p2.name}")

  // Collections -----------------
  val persons: List[PersonModel] = PersonModel.initPersons(4)
  println("Persons : " + persons)

  // methods(Case Class Definition)
  println(p2.older(1))
  println(p2 older 1)
  println(p2 older {
    1
  })

  // for comprehension and flatMap
  val jobs: List[Job] = Job.initJobs
  println(jobs)
  println(Job.getPersonsWithJob(jobs))

  // other functionnal stuff
  println(PersonModel.changeAllName(persons, "Donald"))

}
