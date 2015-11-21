package com.practice

object ExtensibilityAndModularity extends App {

  trait Adding {
    def add(a: Int, b: Int) = a + b
  }

  trait Multiplying {
    def mul(a: Int, b: Int) = a * b
  }

  trait Calculator extends Adding with Multiplying

  // Objet instancie et cree un seule fois
  case object CalculatorImpl extends Calculator

  // A instancier
  case class  CalculatorImplBis(name: String) extends Calculator

  // Tests
  println(CalculatorImpl.add(1, 2))
  println(CalculatorImplBis("My calculator").add(1, 2))

  //--------------------------------

  trait Simple {
    def value = 7
  }

  trait Doubling extends Simple {
    override def value = super.value * 2
  }

  trait Tripling extends Simple {
    override def value = super.value * 3
  }
  val mixin = new Simple with Doubling with Tripling
  println(mixin.value) // What is printed?

}
