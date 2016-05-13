package com.practice.play2

import play.api.libs.json._

/**
 * Created by galloisg on 12/05/2016.
 */
object TestJSON extends App {

  println("\nTestJSON -----------------------------")
  // examples from https://www.playframework.com/documentation/2.4.x/ScalaJson
  // and https://www.playframework.com/documentation/2.4.x/ScalaJsonCombinators

  val json: JsValue = Json.parse("""
  {
    "name" : "Watership Down",
    "location" : {
      "lat" : 51.235685,
      "long" : -1.309197
    },
    "residents" : [ {
      "name" : "Fiver",
      "age" : 4,
      "role" : null
    }, {
      "name" : "Bigwig",
      "age" : 6,
      "role" : "Owsla"
    } ]
  }
  """)
  println(json)

  // {"factures":{"numBordereau":26030491180,"montant":1000.0,"nbDocs":1},"avoirs":{"numBordereau":26030491181,"montant":100.0,"nbDocs":1}}
  val json2: JsValue = Json.parse("""
  {
    "factures" : {
    "numBordereau":26030491180,
    "montant":1000.0,
    "nbDocs":1
    }
  }
  """)
  println(json2)

  // Traversing a JsValue structure
  println("\nTraversing a JsValue structure --------")
  val lat = (json \ "location" \ "lat").get
  println(s"${lat}")

  val names = json \\ "name"
  println(s"${names}")

  val bigwig = (json \ "residents")(1).get
  println(s"${bigwig}")

  val factures = (json2 \ "factures" \ "numBordereau")
  println(s"${factures}")

  val mayBefacture = (json2 \ "factures")
  val numBordereauOK = mayBefacture match {
    case js: JsUndefined => {
      println("KO : " + js)
      "0"
    }
    case js: JsDefined => {
      println("OK : " + js)
      (js \ "numBordereau").get
    }
  }
  println(s"${numBordereauOK}")

  val numBordereau = json2 \\ "numBordereau"
  println(s"${numBordereau}")

  // Converting from a JsValue
  println("\nConverting from a JsValue ------------")
  val name = (json \ "name").as[String]
  println(s"${name}")


}
