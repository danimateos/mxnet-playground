name := "mxnet-playground"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "ml.dmlc.mxnet" % "mxnet-full_2.10-linux-x86_64-gpu" % "0.1.1"

// Needed for javaOptions
fork in run := true

javaOptions += "-Xmx4G"

envVars := Map(  
  "mnist-data-path" -> "data",
  "gpu" -> "1",
  "output-path" -> "out"
)
