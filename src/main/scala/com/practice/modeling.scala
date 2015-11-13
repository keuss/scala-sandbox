package com.practice

import doodle.core._
import doodle.jvm._

object Modeling {

  // exo
  sealed trait Level

  case class Cource(level: Level, name: String)

  //different levels ici création à chaque fois d'une instance !
  //case class Beginner() extends Level
  //case class Intermediate() extends Level
  //case class Advanced() extends Level

  //case objects car pas de param dans les levels
  //ici 3 instance crééé une seule fois
  case object Beginner extends Level
  case object Intermediate extends Level
  case object Advanced extends Level


  val cour = Cource(Beginner, "cour debutant")


  // exo
  sealed trait Expression
  case class Number(value: Int) extends Expression
  case class Addition(leftHand: Expression, rightHand: Expression) extends Expression

  val n1 = Number(1)
  val n2 = Number(2)
  val n3 = Number(3)
  val res = Addition(n1, Addition(n2, n3))

}
