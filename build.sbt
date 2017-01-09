name := "mxnet-playground"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "ml.dmlc.mxnet" % "mxnet-full_2.11-linux-x86_64-gpu" % "0.1.2-SNAPSHOT"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

// Needed for javaOptions
fork in run := true

javaOptions += "-Xmx4G"

envVars := Map(  
  "mnist-data-path" -> "data",
  "gpu" -> "1",
  "output-path" -> "out"
)
