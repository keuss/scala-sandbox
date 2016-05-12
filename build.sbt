name := "ScalaPractice"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Underscore Training" at "https://dl.bintray.com/underscoreio/training"

resolvers += "Sonatype OSS Releases"  at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"


libraryDependencies ++= Seq(
  "underscoreio" %% "doodle" % "0.1.0",
  "com.typesafe.play" %% "play-ws" % "2.4.0",
  "com.typesafe.play" %% "play" % "2.4.0",
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.5",
  "com.chuusai" %% "shapeless" % "2.0.0"
)
    