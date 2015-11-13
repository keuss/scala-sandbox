package com.practice

object FirstclassFunctions extends App {

  def square(a: Int) = a * a

  def squareWithBlock(a: Int) = {
    a * a
  }

  val squareVal = (a: Int) => a * a

  def addOne(f: Int => Int, arg: Int) = f(arg) + 1

  println("square(2):" + square(2))
  println("squareWithBlock(2):" + squareWithBlock(2))
  println("squareVal(2):" + squareVal(2))
  println("addOne(squareVal,2):" + addOne(squareVal, 2))

  println("-----FONCTION AS VARIABLE-----")
  def addTag(tag:String): (String => String) = {
    (text:String) => "<" + tag + ">" + text + "</" + tag + ">"
  }

  val addSpan = addTag("span")
  println(addSpan("hello !"))
  println(addSpan("bye !"))

}
