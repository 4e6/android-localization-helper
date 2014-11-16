name := "android-localization-helper"

version := "0.2-SNAPSHOT"

scalaVersion := "2.11.4"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint")

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.2"

enablePlugins(JavaAppPackaging)
