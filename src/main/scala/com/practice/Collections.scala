package com.practice


import doodle.core._

object Collections {

  /**
   * @return a sequence whose elements are the elements of `xs` incremented by one
   */
  def inc(xs: Seq[Int]): Seq[Int] = xs match {
    case Nil => Seq.empty[Int]
    case x +: xsTrails => (x + 1) +: inc(xsTrails)
  }

  def incMap(xs: Seq[Int]): Seq[Int] = xs.map(v => v + 1)

  /**
   * @return a sequence whose elements are the length of the elements of `ss`
   */
  def lengths(ss: Seq[String]): Seq[Int] = ss match {
    case Nil => Seq.empty[Int]
    case s +: ssTrails => s.length +: lengths(ssTrails)
  }

  def lengthsMap(ss: Seq[String]): Seq[Int] = ss.map(v => v.length)


  def sum(xs: Seq[Int]): Int = ???

  def product(xs: Seq[Int]): Int = ???

  /**
   * Returns `n` concentric circles
   */
  //def circles(n: Int): Seq[Circle] = ???

  /**
   * Returns `n` concentric circles (recursive implementation)
   */
  def circlesRec(n: Int): Seq[Circle] = {
    val unit = Circle(20 + 4 * n)
    if(n == 1)
      Seq(unit)
    else
      (unit +: circlesRec(n -1))
  }


  /**
   *  Return a stack on Image
   */
  def stack(images: Seq[Image]): Image = images match {
    // pattern matching
    case Nil => Circle(0).fillColor(Color.white)
    case img +: imgsTrail => img on stack(imgsTrail)
  }

  def spiral(n: Int): Seq[Image] = ???

  def spiralRec(n: Int): Seq[Image] = {
    val a = Angle.degrees(n * 30)
    val x = a.cos * (n * 10)
    val y = a.sin * (n * 10)
    val unit = Circle(20 + 2 * n).at(x, y)
    if(n == 1) Seq(unit)
    else (unit +: spiralRec(n-1))
  }
}