package com.practice


import doodle.core._

object Collections {

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

}