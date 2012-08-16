import ScalaxbKeys._

name := "AOJ4s"

organization := "com.github.arosh"

scalaVersion := "2.9.2"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-explaintypes")

libraryDependencies ++= Seq(
  "net.databinder" %% "dispatch-http" % "0.8.8",
  "org.specs2" %% "specs2" % "1.12" % "test"
)

parallelExecution in Test := false

// scalaxb setting
seq(scalaxbSettings: _*)

// packageName in scalaxb in Compile := "com.github.arosh.aoj4s.info"

packageNames in scalaxb in Compile := Map(
  uri("http://github.com/arosh/AOJ4s/ContestInfo") -> "com.github.arosh.aoj4s.info.contestinfo",
  uri("http://github.com/arosh/AOJ4s/ContestList") -> "com.github.arosh.aoj4s.info.contestlist",
  uri("http://github.com/arosh/AOJ4s/ContestProblem") -> "com.github.arosh.aoj4s.info.contestproblem",
  uri("http://github.com/arosh/AOJ4s/ContestStanding") -> "com.github.arosh.aoj4s.info.conteststanding",
  uri("http://github.com/arosh/AOJ4s/ContestStatusLog") -> "com.github.arosh.aoj4s.info.conteststatuslog",
  uri("http://github.com/arosh/AOJ4s/Judge") -> "com.github.arosh.aoj4s.info.judge",
  uri("http://github.com/arosh/AOJ4s/Problem") -> "com.github.arosh.aoj4s.info.problem",
  uri("http://github.com/arosh/AOJ4s/ProblemCategory") -> "com.github.arosh.aoj4s.info.problemcategory",
  uri("http://github.com/arosh/AOJ4s/ProblemList") -> "com.github.arosh.aoj4s.info.problemlist",
  uri("http://github.com/arosh/AOJ4s/SolvedRecord") -> "com.github.arosh.aoj4s.info.solvedrecord",
  uri("http://github.com/arosh/AOJ4s/Source") -> "com.github.arosh.aoj4s.info.source",
  uri("http://github.com/arosh/AOJ4s/StatusLog") -> "com.github.arosh.aoj4s.info.statuslog",
  uri("http://github.com/arosh/AOJ4s/User") -> "com.github.arosh.aoj4s.info.user",
  uri("http://github.com/arosh/AOJ4s/UserList") -> "com.github.arosh.aoj4s.info.userlist"
)

protocolPackageName in scalaxb in Compile := Some("com.github.arosh.aoj4s.info")

sourceGenerators in Compile <+= scalaxb in Compile

// sbteclipse with source
EclipseKeys.withSource := true

// sbt-onejar
seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)
