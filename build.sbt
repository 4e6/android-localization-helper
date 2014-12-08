name := "android-localization-helper"

organization := "me.4e6"

version := "0.3"

scalaVersion := "2.11.4"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint")

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.2"

enablePlugins(JavaAppPackaging)

seq(bintraySettings: _*)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
