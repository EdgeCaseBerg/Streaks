name := "streaks"

version := "0.1"

scalaVersion := "2.10.4"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += "com.typesafe.slick" %% "slick" % "2.0.1"

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.6.4"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.12"