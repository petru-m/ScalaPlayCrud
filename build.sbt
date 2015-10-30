name := "ScalaPlayCrud"

version := "1.0"

lazy val `scalaplaycrud` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.35",
  "com.typesafe.play" %% "anorm" % "2.4.0",
  "com.typesafe.play" %% "play-slick" % "1.0.0",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "com.h2database" % "h2" % "1.4.187",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.0.0",
  evolutions,
  play.sbt.Play.autoImport.cache // only when you use default IdContainer
)
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

fork in Test := true

testOptions in Test += Tests.Argument("sequential")

javaOptions in Test += "-Dconfig.file=conf/application.testing.conf"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.

routesGenerator := InjectedRoutesGenerator