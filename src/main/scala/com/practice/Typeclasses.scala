package com.practice

object Typeclasses {

  /**
   * Implement the following generic `sum` method
   */
  def sum[A](as: Seq[A])(implicit numeric: Numeric[A]): A = as match {
    case Nil => numeric.zero
    case a +: asTrail => numeric.plus(a, sum(asTrail))
  }

  //avec foldLeft (une autre façon de faire du pattern matching)
  def sumFL[A](as: Seq[A])(implicit numeric: Numeric[A]): A =
    as.foldLeft(numeric.zero)(numeric.plus)


  def sumInt(as: Seq[Int]): Int = as match {
    case Nil => 0
    case a +: asTrail => a + sum(asTrail)
  }

}
