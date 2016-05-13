package com.practice.play2

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._

/**
 * Created by galloisg on 12/05/2016.
 */
object TestJSON extends App {


  // Converting to a JsValue (write = Object to Json)
  println("\nConverting to a JsValue ------------")
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

  val json1: JsValue = Json.obj(
    "name" -> "Watership Down",
    "location" -> Json.obj("lat" -> 51.235685, "long" -> -1.309197),
    "residents" -> Json.arr(
      Json.obj(
        "name" -> "Fiver",
        "age" -> 4,
        "role" -> JsNull
      ),
      Json.obj(
        "name" -> "Bigwig",
        "age" -> 6,
        "role" -> "Owsla"
      )
    )
  )
  println(json1)

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

  // see https://groups.google.com/forum/#!topic/play-framework/Bs5pEy2nAzs
  // a JsObject is a JsValue.
  val person1: JsValue = Json.obj(
    "user" -> "Max",
    "friend" -> "Bob")
  println(s"${person1}") // {"user":"Max","friend":"Bob"}

  // <=>

  val person2: JsValue = Json.parse("""
  {
    "user": "Marc",
    "friend": "Steve"
  }
  """)
  println(person2) // {"user":"Marc","friend":"Steve"}

  println("\nConverting to a JsValue write ------")
  // writes
  case class Location(lat: Double, long: Double)
  case class Resident(name: String, age: Int, role: Option[String])
  case class Place(name: String, location: Location, residents: Seq[Resident])

  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and
    (JsPath \ "long").write[Double]
  )(unlift(Location.unapply))

  implicit val residentWrites: Writes[Resident] = (
    (JsPath \ "name").write[String] and
    (JsPath \ "age").write[Int] and
    (JsPath \ "role").writeNullable[String]
  )(unlift(Resident.unapply))

  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "name").write[String] and
    (JsPath \ "location").write[Location] and
    (JsPath \ "residents").write[Seq[Resident]]
  )(unlift(Place.unapply))

  val place = Place(
    "Watership Down",
    Location(51.235685, -1.309197),
    Seq(
      Resident("Fiver", 4, None),
      Resident("Bigwig", 6, Some("Owsla"))
    )
  )

  val jsonPlace = Json.toJson(place)
  println(s"${jsonPlace}")
  val jsonPlaceKO = Json.toJson(Place(
    "Watership Down",
    Location(-1251.235685, -1.309197),
    Seq(
      Resident("Fiver", 4, None),
      Resident("Bigwig", 6, Some("Owsla"))
    )
  ))

  println("\nConverting to a JsValue read -------")
  // read = Json to Object
  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double](min(-90.0) keepAnd max(90.0)) and
    (JsPath \ "long").read[Double](min(-180.0) keepAnd max(180.0))
  )(Location.apply _)

  implicit val residentReads: Reads[Resident] = (
    (JsPath \ "name").read[String](minLength[String](2)) and
    (JsPath \ "age").read[Int](min(0) keepAnd max(150)) and
    (JsPath \ "role").readNullable[String]
  )(Resident.apply _)

  implicit val placeReads: Reads[Place] = (
    (JsPath \ "name").read[String](minLength[String](2)) and
    (JsPath \ "location").read[Location] and
    (JsPath \ "residents").read[Seq[Resident]]
  )(Place.apply _)

  // test with jsonPlaceKO
  jsonPlace.validate[Place] match {
    case s: JsSuccess[Place] => {
      val place: Place = s.get
      // do something with place object
      println(s"validate place : ${place}")
    }
    case e: JsError => {
      // error handling flow
      println(s"error : ${e}")
    }
  }

  // Traversing a JsValue structure
  println("\nTraversing a JsValue structure -----")
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
  println("\nConverting from a JsValue ----------")
  val name = (json \ "name").as[String]
  println(s"${name}")


}
