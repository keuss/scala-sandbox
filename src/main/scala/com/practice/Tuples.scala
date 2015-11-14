package com.practice

object Tuples extends App {

  sealed trait FitnessDevice

  case class Barbell(load: Int, length: Int) extends FitnessDevice {
    def weigh: Barbell = Barbell(load + 10, length + 20)
    def lighten: Barbell = Barbell(load - 2, length - 20)
  }

  case class Mat(width: Int, length: Int) extends FitnessDevice {
    // utilisation Option
    def smaller: Option[Mat] =
      if (width <= 15 || length <= 100) None
      else Some(Mat(width - 10, length - 20))
  }

  val fitnessEquipment:(FitnessDevice, FitnessDevice, FitnessDevice) = (Barbell(30, 50), Barbell(20, 50), Mat(30, 100))
  println(fitnessEquipment)
  println(fitnessEquipment._2)

}
