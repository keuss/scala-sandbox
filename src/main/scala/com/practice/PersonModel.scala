package com.practice

import scala.collection.mutable.ListBuffer
import scala.util.Random

case class PersonModel(name: String, age: Int, job: String = "Unemploy") {

  /**
   * older method
   * @param years to add
   * @return an older person
   */
  def older(years: Int): PersonModel = PersonModel(name, age + years)

}

object PersonModel {

  // "static" methods
  /**
   * initPersons
   * @param nb number of person to get
   * @return list of person models
   */
  def initPersons(nb: Int): List[PersonModel] = {
    val sPerson = ListBuffer[PersonModel]()
    // for loop execution with a range
    for( a <- 1 to nb) {
      sPerson += PersonModel(s"Mike${Utils.randomAlphaNumericString(5)}", Random.nextInt(100))
    }
    // last statement => return !
    sPerson.toList
  }

  /**
   * changeAllName
   * @param persons list of person
   * @param newName the new name for all person
   * @return list of person with updated name
   */
  def changeAllName(persons: List[PersonModel], newName: String): List[PersonModel] = {
    persons.map( p => PersonModel(newName, p.age))
  }

  /**
   * findByName
   * @param persons list of person
   * @param name the name to find by
   * @return person if finded
   */
  def findByName(persons: List[PersonModel], name: String): Option[PersonModel] = {
    persons.find( p => p.name == name)
  }

}