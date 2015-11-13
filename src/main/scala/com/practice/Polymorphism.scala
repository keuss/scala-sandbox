package com.practice

object Polymorphism {

  /**
   * Implement the following method that takes a collection at parameter and returns its number of elements
   */
  def size[A](as: Seq[A]): Int = as match {
    case Nil => 0
    case a +: asTrail => 1 + size(asTrail)
  }

  /**
   * Implement the following method that concatenates two sequences
   ici as1 eest devant as2
   */
  def concat[A](as1: Seq[A], as2: Seq[A]): Seq[A] = as1 match {
    case Nil => as2
    case a1 +: as1Trail => a1 +: concat(as1Trail, as2)
  }

  /**
   * Implement the following method that reverses a sequence
   */
  def reverse[A](as: Seq[A]): Seq[A] = as match {
    case Nil => Nil
    case a +: asTrail => reverse(asTrail) :+ a
  }

}

/**
 * Write a polymorphic Col[A] data type
 */
sealed trait Col[A] {
  def size: Int = ???
  def concat(as: Col[A]): Col[A] = ???
  def reverse: Col[A] = ???
  def map[B](f: A => B): Col[B] = ???
  def filter(p: A => Boolean): Col[A] = ???
  def forall(p: A => Boolean): Boolean = ???
  def exists(p: A => Boolean): Boolean = ???
  def fold[B](b: B)(f: (B, A) => B): B = ???
}

case class Empty[A]() extends Col[A]
case class OneAnd[A](a: A, tail: Col[A]) extends Col[A]
