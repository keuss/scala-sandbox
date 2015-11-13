package com.practice

import doodle.core._
import doodle.jvm._

object Main extends App {

  // --- Program entry point

  //draw(Circle(50)) // Complete the exercises and change this line to test your code (e.g. `draw(circles(10))`)

  //draw(circles(10))

  // --- Exercises
  //draw(spiral(10))

  //draw(sierpinski(10))


  sealed trait FitnessDevice


  def unitCircle(n: Int) = Circle(5 + 10 * n)

  def unitCircleAt(n: Int, x: Double, y: Double) = Circle(20 + 2 * n).at(x, y)

  def circles(n: Int): Image =
    if(n == 1) unitCircle(n)
    else (unitCircle(n) on circles(n -1))

  def spiral(n: Int): Image = {
    val a = Angle.degrees(n * 30)
    val x = a.cos * (n * 10)
    val y = a.sin * (n * 10)
    val unit = unitCircleAt(n, x, y)
    if(n == 1) unitCircleAt(n, x , y)
    else (unitCircleAt(n, x, y) on spiral(n -1))
  }


  def unitTriangle(n: Int) = Triangle(n, n)
  def unitTriangleBlack(n: Int) = Triangle(n, n) fillColor Color.black


  def sierpinski(n: Int): Image =
    if(n == 1)  unitTriangleBlack(8)
    else {
      val t = sierpinski(n -1)
      t above (t beside t)
    }

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


  def smallerWidth(mat: Mat): Option[Int] =
    mat.smaller.map(smallerMat => smallerMat.width)

  //Write a method that takes a Mat as parameter, tries to get a
  //smaller mat and returns its area only if it is higher than 1000
  def smallerButLargeEnough(mat: Mat): Option[Int] =
    mat.smaller
      .map(sm => sm.width * sm.length)  //ici un option de surface
      .filter(area => area > 1000)      // puis on filtre l'option d'int


  def barbellImage(barbell: Barbell): Image = {
    val weight = Rectangle(barbell.load, barbell.load).fillColor(Color.black)
    val bar = Rectangle(barbell.length, 5)
    // dernière ligne <-> return
    weight beside bar beside weight
  }

  /**
   * Implement an instance of `Ordering[Mat]` that compares the area of the mats
   *  new Ordering[Mat] { } class implicit comme en java
   */
  implicit val matOrdering: Ordering[Mat] = new Ordering[Mat] {
    // on implémente compare de math.Ordering (cf doc)
    // http://scala-lang.org/api/current/#scala.math.Ordering
    def compare(m1: Mat, m2: Mat): Int =
      m1.width * m1.length - m2.width * m2.length
  }

  def matImage(mat: Mat): Image = Rectangle(mat.width, mat.length)

  val lightBarbell = Barbell(10, 180)
  val heavyBarbell = lightBarbell.weigh
  val lighterBarbell = lightBarbell.lighten
  //draw(barbellImage(lightBarbell))
  //draw(barbellImage(lighterBarbell))

  //lightBarbell.load = 10 //impossible



  // pattern matching
  def fitnessDeviceImage(fitnessDevice: FitnessDevice): Image = fitnessDevice match {
    case b: Barbell => barbellImage(b)
    case m: Mat => matImage(m)
  }

  /**
   * Define a method that takes a sequence of Mat and returns their areas if this one is greater than 1000
   */
  def largeEnough(mats: Seq[Mat]): Seq[Int] =
    mats
      .map(v => v.width * v.length)
      .filter(air => air > 1000)


  def largeEnoughBis(mats: Seq[Mat]): Seq[Int] =
    for {
      mat <- mats //Seq donc retour Seq
      air = mat.width * mat.length
      if air > 1000
    } yield air


  // 19.17 Exercise
  // Define a type class Show[A] that captures the ability to
  // compute an image for a given value.
  // Implement an instance of Show[FitnesDevice]
  // sans le - KO (faire le test)
  trait Show[-A] {
    def compute(a: A): Image
  }

  implicit val showFitnessDevice: Show[FitnessDevice] = new Show[FitnessDevice] {

    def barbellImage(b: Barbell): Image = {
      val weight = Rectangle(b.load, b.load).fillColor(Color.black)
      val bar = Rectangle(b.length, 5)
      // dernière ligne <-> return
      weight beside bar beside weight
    }

    def matImage(m: Mat): Image = {
      Rectangle(m.width, m.length).fillColor(Color.black)
    }

    // implément compute pour un FitnesDevice
    def compute(fd: FitnessDevice): Image = fd match {
      case barbell @ Barbell(load, length) => barbellImage(barbell)
      // ou si on s'en fou des valeur du barbell qui match :
      //case barbell : Barbell => barbellImage(barbell)
      case mat @ Mat(width, length) => matImage(mat)
      //case mat : Mat => matImage(mat)
    }

  }

  def genericDraw[A](a: A)(implicit show: Show[A]): Unit =
    draw(show.compute(a))
  // fin exo 19.17

  // test
  //draw(fitnessDeviceImage(lightBarbell))
  //draw(fitnessDeviceImage(Mat(20, 60)))

  //Collections
  //println(Collections.circles(4))
  //println(Collections.spiralRec(4))

  //draw(Collections.stack(Collections.circles(10)))
  //draw(Collections.stack(Collections.spiralRec(10)))

  val numbers = Seq(1,2,3)
  println(Collections.inc(numbers))
  println(Collections.lengths(Seq("1", "22", "333")))
  println(Collections.incMap(numbers))
  println(Collections.lengthsMap(Seq("1", "22", "333")))

  val myMats = Seq(Mat(50, 50), Mat(101, 3), Mat(300, 50), Mat(50, 50), Mat(5, 5))
  println(largeEnough(myMats))

  // test option mat
  println("Test option mat")
  val mat1 = Mat(50, 50)
  println(mat1.smaller)
  val mat2 = Mat(100, 200)
  println(mat2.smaller)
  println(smallerWidth(mat2))
  println("Test filter option")
  println(smallerButLargeEnough(Mat(20, 120)))
  println(smallerButLargeEnough(Mat(20, 121)))
  println("Test Sucre syntaxique")
  val n = 32
  println("Hello, i am " ++ n.toString ++ " years old")
  println(s"Hello, i am $n years old")
  // Test for
  println(largeEnoughBis(myMats))

  // Test GameOfLife 100 déjà ko ...
  //GameOfLife.start(50)

  //test FP et OOP
  //expr.fp.Main
  //expr.oop.Main

  // Polymorphism
  val seqString: Seq[String] = Seq("1", "2", "3")
  val seqString2: Seq[String] = Seq("4", "5", "6")
  val seqInt: Seq[Int] = Seq(1, 2, 3, 4)
  //println(Polymorphism.size(seqString))
  //println(Polymorphism.size(seqInt))

  //println(Polymorphism.concat(seqString, seqString2))

  //println(Polymorphism.reverse(seqInt))

  // test fold
  val nums = List(1, 2, 3, 4)
  val sum = nums.foldLeft(0) (
    (acc, num) => acc + num
  )
  //println(sum)
  // <=>
  //println(nums.foldLeft(0)(_ + _))

  //Typeclasses
  println("Typeclasses tests")
  //println(Typeclasses.sumInt(seqInt))
  //println(Typeclasses.sum(Seq(1, 2, 3)))
  //println(Typeclasses.sumFL(Seq(1, 2, 3)))

  // Ordering Mat via le sorted de la Seq :
  // def sorted[B >: A](implicit ord: math.Ordering[B]): Seq[A]
  //println(myMats.sorted)

  // Test exo 19.17 (test sans le - dans le type class Show[A])
  val fd: FitnessDevice = Barbell(20, 200)
  genericDraw(fd)
  genericDraw(Barbell(20, 100))
  genericDraw(Mat(100, 100))
  println("YOOOOOOOOOOOOOOOOO")
}
