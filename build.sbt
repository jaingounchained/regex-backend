name := "regex-backend"
version := "1.0"
scalaVersion := "2.13.8"

lazy val `regex-parser` = (project in file("regex-parser"))
  .settings(
    name := "regex-parser",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "fastparse" % Version.fastparse
    ),
    // Test dependencies
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % Version.scalatest,
      "org.scalatestplus" %% "scalacheck-1-14" % Version.scalatestplus
    ).map(_ % Test)
  )

lazy val `regex-evaluator` = (project in file("regex-evaluator"))
  .dependsOn(`regex-parser` % "compile->compile;test->test")
  .settings(
    name := "regex-evaluator",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % Version.cats
    ),
    // Test dependencies
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % Version.scalatest,
      "org.scalatestplus" %% "scalacheck-1-14" % Version.scalatestplus,
      "org.typelevel" %% "cats-laws" % Version.cats
    ).map(_ % Test)
  )