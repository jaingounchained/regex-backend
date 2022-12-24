name := "Regex"
version := "1.0"
scalaVersion := "2.13.8"

lazy val `regex-parser` = (project in file("regex-parser"))
    .settings(
        name := "regex-parser",
        libraryDependencies ++= Seq(
            "com.lihaoyi" %% "fastparse" % "2.2.2"
        ),
        // Test dependencies
        libraryDependencies ++= Seq(
            "org.scalatest" %% "scalatest" % "3.2.0",
            "org.scalatestplus" %% "scalacheck-1-14" % "3.2.0.0"
        ).map(_ % Test)
    )