name := "myHbaseTest"

version := "1.0"


libraryDependencies ++= {
  val akkaV = "2.3.5"
  val sprayV = "1.3.2"
  Seq(
    "org.apache.hbase" % "hbase-client" % "0.98.6-cdh5.3.2" exclude("org.slf4j", "slf4j-log4j12"),
    "org.apache.hbase" % "hbase-common" % "0.98.6-cdh5.3.2" exclude("org.slf4j", "slf4j-log4j12"),
    "com.zte.zdh" % "spark-assembly" % "1.4.1.2-cdh5.3.2" exclude("org.slf4j","slf4j-log4j12")
  )
}
    