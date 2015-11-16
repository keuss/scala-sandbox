package com.practice

import doodle.core._
import doodle.jvm._


object LoopsAndConditionals extends App {

  //Collections

  val numbers = Seq(1,2,3)
  println(Collections.inc(numbers))
  println(Collections.lengths(Seq("1", "22", "333")))
  println(Collections.incMap(numbers))
  println(Collections.lengthsMap(Seq("1", "22", "333")))


  //println(Collections.circlesRec(4))
  //draw(Collections.stack(Collections.circlesRec(4)))

  //TODO implement stackBis
  //draw(Collections.stackBis(Collections.circles(4)))


}
