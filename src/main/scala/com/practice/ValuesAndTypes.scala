package com.practice

import doodle.core._
import doodle.jvm._


object ValuesAndTypes extends App {

  // Expressions and Values
  // In Scala, almost everything is an expression.
  var helloWorld = "hello" + " world"
  println(helloWorld)

  val again = " again"
  helloWorld = helloWorld + again
  println(helloWorld)

  // Define variable with his type
  val againBis: String = " !"
  helloWorld = helloWorld + againBis
  println(helloWorld)

  // draw exemple
  //draw(Rectangle(30, 50))

  //draw(Rectangle(30, 80) fillColor Color.black)

  //draw(Rectangle(60, 100) beside Circle(30))
  // <=>
  //draw(Rectangle(60, 100).beside(Circle(30)))

  /*
  draw(
    (Rectangle(25, 100) fillColor Color.black) beside
      (Rectangle(200, 20) fillColor Color.grey) beside
      (Rectangle(25, 100) fillColor Color.black)
  )
  */

  /*
  val weight = Rectangle(25, 100) fillColor Color.black
  val bar = Rectangle(200, 20) fillColor Color.grey
  draw(weight beside bar beside weight)
  */


}
