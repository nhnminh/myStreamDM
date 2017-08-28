/*
 * Copyright (C) 2015 Holmes Team at HUAWEI Noah's Ark Lab.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.spark.streamdm.evaluation

import java.io.Serializable
import scalaz._
import Scalaz._
import org.apache.spark.streamdm.core.Example
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streamdm.tasks.AccuracyAggregator

/**
 * Single label binary classification evaluator which computes the confusion
 * matrix from a stream of tuples composes of the testing Examples and doubles
 * predicted by the learners.
 */
class BasicClassificationEvaluator extends Evaluator{
  var numInstancesCorrect = 0.0; 
  var numInstancesSeen = 0.0;
 
  

  /**
   * Process the result of a predicted stream of Examples and Doubles.
   *
   * @param input the input stream containing (Example,Double) tuples
   * @return a stream of String with the processed evaluation
   */
//   override def addResult(input: DStream[(Example, Double)], option: Int, numClasses: Int, valueOfClass: Array[String]): DStream[(Int, Int)] = {
//     //print the confusion matrix for each batch
    
//     // if option = 0 --> only accuracy is shown 
//     // if option = 1 --> confusion matrix is shown 

//     //numClasses determine which type of confusion matrix will be shown.

//     val pred = ConfusionMatrix.computeMatrix(input)
//     val predMC = ConfusionMatrix.computeMatrix(input, numClasses, valueOfClass)

//     // show full confusion matrix 
//     if (option == 1){
//       if(numClasses < 3){
//         pred.map(x => {
//           // "%.3f,%.0f,%.0f,%.0f,%.0f".format((x._1+x._4)/(x._1+x._2+x._3+x._4),x._1,x._2,x._3,x._4)})
//         (numInstancesCorrect.toInt,numInstancesSeen.toInt)
//     })}
//       else{pred.map(x=>(numInstancesCorrect.toInt,numInstancesSeen.toInt))
//         // predMC.map(x => "Not implemented")
        
//       }
      
//     }
  
//     // only show accuracy. Option != 1 
//     else {
//       if (numClasses < 3){
//         // 2 classes only 
//         pred.map(x => {
//           this.numInstancesCorrect += (x._1 + x._4)
//           this.numInstancesSeen += (x._1+x._2+x._3+x._4)
//           // "Each Rdd Accuracy : %.3f".format((x._1+x._4)/(x._1+x._2+x._3+x._4))})
//           // "Accuracy : %.3f, Correct: %.3f, Total %.3f".format(this.numInstancesCorrect/this.numInstancesSeen, this.numInstancesCorrect, this.numInstancesSeen)})
//         (numInstancesCorrect.toInt,numInstancesSeen.toInt)})
//       }
//       else{
//         //multiclass 
//         predMC.map(x => {
//           this.numInstancesCorrect += x._1
//           this.numInstancesSeen += (x._1 + x._2)
//           // "Each Rdd Accuracy : %.3f".format(x._1/(x._1+x._2))})
//           // "Accuracy : %.3f, Correct: %.3f, Total %.3f".format(this.numInstancesCorrect/this.numInstancesSeen, this.numInstancesCorrect, this.numInstancesSeen)})
//         (numInstancesCorrect.toInt,numInstancesSeen.toInt)
//         // predMC.map(x => "Not implemented")
//       })}

//       // b.map( x => {for (var i=0 <- 0 to 2){"%.3f".format(x(i)(i))}}
      
//     }
          
// }

override def addResult(input: DStream[(Example, Double)], option: Int, numClasses: Int, valueOfClass: Array[String], accAggregator: AccuracyAggregator): DStream[(String)] = {
    //print the confusion matrix for each batch
    
    // if option = 0 --> only accuracy is shown 
    // if option = 1 --> confusion matrix is shown 

    //numClasses determine which type of confusion matrix will be shown.

    val pred = ConfusionMatrix.computeMatrix(input)
    val predMC = ConfusionMatrix.computeMatrix(input, numClasses, valueOfClass)

    // show full confusion matrix 
    if (option == 1){
      if(numClasses < 3){
        pred.map(x => {
          "%.3f,%.0f,%.0f,%.0f,%.0f".format((x._1+x._4)/(x._1+x._2+x._3+x._4),x._1,x._2,x._3,x._4)})
        // (numInstancesCorrect.toInt,numInstancesSeen.toInt)})
    }
      else{pred.map(x=>(numInstancesCorrect.toInt,numInstancesSeen.toInt))
        predMC.map(x => "Not implemented")
        
      }
      
    }
  
    // only show accuracy. Option != 1 
    else {
      if (numClasses < 3){
        // 2 classes only 
        pred.map(x => {
          this.numInstancesCorrect += (x._1 + x._4)
          this.numInstancesSeen += (x._1+x._2+x._3+x._4)
          // "Each Rdd Accuracy : %.3f".format((x._1+x._4)/(x._1+x._2+x._3+x._4))})
          // "Accuracy : %.3f, Correct: %.3f, Total %.3f".format(this.numInstancesCorrect/this.numInstancesSeen, this.numInstancesCorrect, this.numInstancesSeen)})
//          accAggregator.setAccuracy(this.numInstancesCorrect, this.numInstancesSeen)
//          println("Acc:"+ (this.numInstancesCorrect/this.numInstancesSeen))
//          println("TestAccuracy: " + (accAggregator.getCorrectInstances()/accAggregator.getTotalInstances())
//          + "\t Test correct: " + accAggregator.getCorrectInstances()
//          + "\t Test total: " + accAggregator.getTotalInstances())
          "%.3f,%.3f".format(this.numInstancesCorrect, this.numInstancesSeen)})
        // (numInstancesCorrect.toInt,numInstancesSeen.toInt)})
      }
      else{
        //multiclass 
        predMC.map(x => {
          this.numInstancesCorrect += x._1
          this.numInstancesSeen += (x._1 + x._2)
          // "Each Rdd Accuracy : %.3f".format(x._1/(x._1+x._2))})
          // "Accuracy : %.3f, Correct: %.3f, Total %.3f".format(this.numInstancesCorrect/this.numInstancesSeen, this.numInstancesCorrect, this.numInstancesSeen)})
//          accAggregator.setAccuracy(this.numInstancesCorrect, this.numInstancesSeen)
//          println("Acc:"+ (this.numInstancesCorrect/this.numInstancesSeen))
//          println("TestAccuracy: " + (accAggregator.getCorrectInstances()/accAggregator.getTotalInstances())
//            + "\t Test correct: " + accAggregator.getCorrectInstances()
//            + "\t Test total: " + accAggregator.getTotalInstances())
          "%.3f,%.3f".format(this.numInstancesCorrect, this.numInstancesSeen)})
        // (numInstancesCorrect.toInt,numInstancesSeen.toInt)})
        // predMC.map(x => "Not implemented")
     }

      // b.map( x => {for (var i=0 <- 0 to 2){"%.3f".format(x(i)(i))}}
      
    }
          
}
  /**
   * Get the evaluation result.
   *
   * @return a Double containing the evaluation result
   */
  override def getResult(): Double =
    numInstancesCorrect.toDouble/numInstancesSeen.toDouble
}
/**
 * Helper class for computing the confusion matrix for binary classification.
 */
object ConfusionMatrix extends Serializable{

  def confusionMulticlass(x: (Example, Double), numClasses : Int, valueOfClass: Array[String]): (Double,Double) = {
    //now only compute accuracy 
    val truePositive = if (x._1.labelAt(0) == x._2) 1.0 else 0.0
    val others = if (x._1.labelAt(0) == x._2) 0.0 else 1.0

    // val map1 = Map ((x._1.labelAt(0),x._2) ->1)
    // map1
    (truePositive, others) 

  }
  
  def confusion(x: (Example,Double)):
  (Double, Double, Double, Double) = {

    //Confusion Matrix for multiple classes : return an Array[(String,String, Double)]
    val a = if ((x._1.labelAt(0)==x._2)&&(x._2==0.0)) 1.0 else 0.0
    val b = if ((x._1.labelAt(0)!=x._2)&&(x._2==0.0)) 1.0 else 0.0
    val c = if ((x._1.labelAt(0)!=x._2)&&(x._2==1.0)) 1.0 else 0.0
    val d = if ((x._1.labelAt(0)==x._2)&&(x._2==1.0)) 1.0 else 0.0
    (a,b,c,d)
  }

  def computeMatrix(input: DStream[(Example,Double)]):
  DStream[(Double,Double,Double,Double)] =
    input.map(x=>confusion(x))
      .reduce((x,y)=>(x._1+y._1,x._2+y._2,x._3+y._3,x._4+y._4))


  def computeMatrix(input: DStream[(Example, Double)], numClasses: Int, valueOfClass: Array[String]): DStream[(Double, Double)] = {
    input.map(x=>confusionMulticlass(x, numClasses, valueOfClass)).reduce((x,y) => (x._1+y._1,x._2+y._2) )
    // input.map(x=>confusionMulticlass(x, numClasses, valueOfClass).reduce((x,y) => x |+| y))



    
  }  
}

