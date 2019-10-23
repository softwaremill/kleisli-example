name := "kleisli-example"

version := "0.1"

scalaVersion := "2.13.1"

// Add Logging capabilities
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

//cats
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0"
