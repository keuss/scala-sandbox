package com.practice

object FirstclassFunctions extends App {

  // Function Type Definition
  // And Function as variable ---

  def square(a: Int) = a * a

  def squareWithBlock(a: Int) = {
    a * a
  }

  val squareVal = (a: Int) => a * a
  val doubleVal: (Int => Int) = (a: Int) => a * 2
  //val doubleVal: (Int) => Int = (a) => a * 2
  //val doubleVal: Int => Int = a => a * 2

  def addOne(f: Int => Int, arg: Int) = f(arg) + 1

  println("square(2):" + square(2))
  println("squareWithBlock(2):" + squareWithBlock(2))
  println("squareVal(2):" + squareVal(2))
  println("addOne(squareVal,2):" + addOne(squareVal, 2))
  println("doubleVal(3):" + doubleVal(3))
  println("addOne(doubleVal,3):" + addOne(doubleVal, 3))

  def addTag(tag:String): (String => String) = {
    (text:String) => "<" + tag + ">" + text + "</" + tag + ">"
  }

  val addSpan = addTag("span")
  println(addSpan("hello !"))
  println(addSpan("bye !"))
  // ----------------------------

  // Call by Name ----------------
  // A way to pass a literal function as a code block

  def execute(sql: String): Boolean = {
    println(s"$sql execution ...")
    true
  }

  def transaction(code: => Boolean) = {
    //connect to DB, grab a connection, start transaction
    //...
    //execute the code
    code
    //if things went ok commit, if not rollback
  }

  transaction {
    execute("INSERT INTO SOME_TABLE...")
  }
  // ----------------------------

  // Higher-order : A function that can
  // Be passed a function / Return a function
  // Closure !
  case class Employee(id: Int, name: String)
  def reallySlowTaxCalculator(emp: Employee): () => Double = {
    () => {
      println(s"reallySlowTaxCalculator called fom emp=${emp.name}")
      Thread.sleep(5000);
      102.21
    }
  }

  def deferTaxCalculate(emp: Employee): () => Double = {
    reallySlowTaxCalculator(emp)
  }

  // We want do the slow reallySlowTaxCalculator now ...
  val empTax = deferTaxCalculate(Employee(1, "Leyla"))
  // do other stuff ...
  // Now calculate tax for Leyla (now we have time ...)
  println(empTax());
  // ----------------------------

  // Others examples

  def printer(data: String, getPrinter: String => Unit) = getPrinter(data)

  printer("Yo !", {
    d => println(d)
  })

  printer("Yo sans ln !", {
    d => print(d)
  })


}
