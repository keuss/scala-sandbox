package com.practice

import scala.collection.mutable.ListBuffer
import scala.util.Random

case class PersonModel(name: String, age: Int, job: String = "Unemploy") {

  /**
   * older method
   * @param years to add
   * @return an older person
   */
  def older(years: Int): PersonModel = ???

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
   * initPersonsWithContact
   * @return list of person models with job and contact data
   */
  def initPersonsWithContact: List[PersonModel] = {
    /*
    val sPerson = ListBuffer[PersonModel]()
    sPerson += PersonModel("Mike", 45, "Engineer", Some(Contact(Some("0615451237"), Some("mike@gmail.com"))))
    sPerson += PersonModel("Lucy", 32, "Engineer", Some(Contact(Some("0614874414"), Some("lucy@gmail.com"))))
    sPerson += PersonModel("John", 26, "Doctor", None)
    sPerson += PersonModel("Anna", 36, "Doctor", Some(Contact(None, Some("anna@gmail.com"))))
    sPerson += PersonModel("Steve", 52, "Engineer", Some(Contact(Some("0614812185"), None)))
    sPerson.toList
    */
    ???
  }

  /**
   * changeAllName
   * @param persons list of person
   * @param newName the new name for all person
   * @return list of person with updated name
   */
  def changeAllName(persons: List[PersonModel], newName: String): List[PersonModel] = {
    ???
  }

  /**
   * findByName
   * @param persons list of person
   * @param name the name to find by
   * @return person if finded
   */
  def findByName(persons: List[PersonModel], name: String): Option[PersonModel] = {
    ???
  }

  /**
   * findHasContact
   * @param persons list of person
   * @return list of person
   */
  def findHasContact(persons: List[PersonModel]): List[PersonModel] = {
    ???
  }



}