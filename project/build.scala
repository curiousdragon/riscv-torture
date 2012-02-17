import sbt._
import Keys._

object BuildSettings
{
  val buildOrganization = "edu.berkeley.cs"
  val buildVersion = "1.1"
  val buildScalaVersion = "2.9.1"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object TortureBuild extends Build
{
  import BuildSettings._

  lazy val torture = Project(id = "torture", base = file("."), settings = buildSettings) aggregate(generator, testrun, overnight)
  lazy val generator = Project(id = "generator", base = file("generator"), settings = buildSettings)
  lazy val testrun = Project(id = "testrun", base = file("testrun"), settings = buildSettings) dependsOn(generator)
  lazy val overnight = Project(id = "overnight", base = file("overnight"), settings = buildSettings) dependsOn(testrun)
}
