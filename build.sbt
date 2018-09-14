name := "test-automation-showcase"

version := "0.1"

scalaVersion := "2.11.0"

libraryDependencies += "info.cukes" % "cucumber-scala_2.11" % "1.2.4"
libraryDependencies += "info.cukes" % "cucumber-java" % "1.2.5"
libraryDependencies += "info.cukes" % "cucumber-junit" % "1.2.4"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.46.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test exclude("junit", "junit-dep")