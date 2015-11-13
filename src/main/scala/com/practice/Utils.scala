package com.practice

import scala.util.Try
import scala.io.Source


object Utils {

  def randomAlphaNumericString(length: Int): String = {
    val chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')
    randomStringFromCharList(length, chars)
  }

  def randomStringFromCharList(length: Int, chars: Seq[Char]): String = {
    val sb = new StringBuilder
    for (i <- 1 to length) {
      val randomNum = util.Random.nextInt(chars.length)
      sb.append(chars(randomNum))
    }
    sb.toString
  }

  def readTextFile(filename: String): Try[List[String]] = {
    Try {
      Source.fromFile(filename).getLines.toList
    }
  }

}
