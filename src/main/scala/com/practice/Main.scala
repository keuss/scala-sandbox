package com.practice

import doodle.core._
import doodle.jvm._

object Main extends App {

  // --- Program entry point

  //sealed trait FitnessDevice

  // TODO
  def unitCircle(n: Int) = ???

  /**
   * Barbell Model
   * @param load
   * @param length
   */
  case class Barbell(load: Int, length: Int) {
      def weigh: Barbell = Barbell(load + 10, length + 20)
      def lighten: Barbell = Barbell(load - 4, length - 20)
  }

  /**
   * barbellImage helper
   * @param barbell
   * @return Image
   */
  def barbellImage(barbell: Barbell): Image = {
    val weight = Rectangle(barbell.load, barbell.load).fillColor(Color.black)
    val bar = Rectangle(barbell.length, 5)
    // dernière ligne <-> return
    weight beside bar beside weight
  }

  val lightBarbell = Barbell(20, 100)
  val heavyBarbell = lightBarbell.weigh
  val lighterBarbell = lightBarbell.lighten
  draw(barbellImage(lightBarbell))
  draw(barbellImage(lighterBarbell))

  // TODO


  // Polymorphism
  val seqString: Seq[String] = Seq("1", "2", "3")
  val seqString2: Seq[String] = Seq("4", "5", "6")
  val seqInt: Seq[Int] = Seq(1, 2, 3, 4)
  println(Polymorphism.size(seqString))
  println(Polymorphism.size(seqInt))

  // TODO
  //println(Polymorphism.concat(seqString, seqString2))
  //println(Polymorphism.reverse(seqInt))
}
