package com.practice.play2

import play.api.libs.json._

/**
 * Created by galloisg on 12/05/2016.
 */
object TestJSON extends App {

  println("\nTestJSON -----------------------------")
  // examples from https://www.playframework.com/documentation/2.4.x/ScalaJson

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

  // Traversing a JsValue structure
  println("\nTraversing a JsValue structure --------")
  val lat = (json \ "location" \ "lat").get
  println(s"${lat}")

  val names = json \\ "name"
  println(s"${names}")

  val bigwig = (json \ "residents")(1).get
  println(s"${bigwig}")

  //TODO other real tests

}
