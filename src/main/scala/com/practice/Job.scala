package com.practice

import scala.collection.mutable.ListBuffer


case class Job(name: String, persons: List[PersonModel])

object Job {

  /**
   * initJobs
   * @return list of job models
   */
  def initJobs: List[Job] = {
    val jobs = ListBuffer[Job]()
    // for loop execution with a range
    jobs += Job("Engineer", PersonModel.initPersons(2))
    jobs += Job("Doctor", PersonModel.initPersons(3))
    jobs += Job("Musician", PersonModel.initPersons(4))
    jobs.toList
  }

  def getPersonsWithJob(jobs: List[Job]): List[PersonModel] = {

    /*
    val sPerson = ListBuffer[PersonModel]()
    jobs.foreach( job => job.persons.foreach( p => sPerson += PersonModel(p.name, p.age, job.name)))
    sPerson.toList
    */

    /*jobs flatMap { job =>
      job.persons map { p =>
        PersonModel(p.name, p.age, job.name)}
    }*/

    // <=>

    //jobs.flatMap( job => job.persons.map( p => PersonModel(p.name, p.age, job.name)))

    // <=>


    for {
      job <- jobs
      p <- job.persons
    } yield PersonModel(p.name, p.age, job.name)

  }

}
