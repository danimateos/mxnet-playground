package playground

import ml.dmlc.mxnet._
import ml.dmlc.mxnet.optimizer.SGD

object DigitsExample extends App {

  // model definition
  val data = Symbol.Variable("data")
  val fc1 = Symbol.FullyConnected(name = "fc1")()(Map("data" -> data, "num_hidden" -> 128))
  val act1 = Symbol.Activation(name = "relu1")()(Map("data" -> fc1, "act_type" -> "relu"))
  val fc2 = Symbol.FullyConnected(name = "fc2")()(Map("data" -> act1, "num_hidden" -> 64))
  val act2 = Symbol.Activation(name = "relu2")()(Map("data" -> fc2, "act_type" -> "relu"))
  val fc3 = Symbol.FullyConnected(name = "fc3")()(Map("data" -> act2, "num_hidden" -> 10))
  val mlp = Symbol.SoftmaxOutput(name = "sm")()(Map("data" -> fc3))

  val trainDataIter = IO.MNISTIter(Map(
    "image" -> "data/train-images-idx3-ubyte",
    "label" -> "data/train-labels-idx1-ubyte",
    "data_shape" -> "(1, 28, 28)",
    "label_name" -> "sm_label",
    "batch_size" -> "50",
    "shuffle" -> "1",
    "flat" -> "0",
    "silent" -> "0",
    "seed" -> "10"))

  val valDataIter = IO.MNISTIter(Map(
    "image" -> "data/t10k-images-idx3-ubyte",
    "label" -> "data/t10k-labels-idx1-ubyte",
    "data_shape" -> "(1, 28, 28)",
    "label_name" -> "sm_label",
    "batch_size" -> "50",
    "shuffle" -> "1",
    "flat" -> "0",
    "silent" -> "0"))

  val model = FeedForward.newBuilder(mlp)
    .setContext(Context.cpu())
    .setNumEpoch(10)
    .setOptimizer(new SGD(learningRate = 0.1f, momentum = 0.9f, wd = 0.0001f))
    .setTrainData(trainDataIter)
    .setEvalData(valDataIter)
    .build()

}
