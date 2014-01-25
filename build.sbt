organization := "com.ergle"

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
    "org.springframework" % "spring-context" % "3.2.2.RELEASE",
    "com.typesafe.play" %% "play" % "2.2.1",
    "org.specs2" %% "specs2" % "2.3.7" % "test",
    "javax.inject" % "javax.inject" % "1",
    "org.mockito" % "mockito-core" % "1.9.5" % "test"
)