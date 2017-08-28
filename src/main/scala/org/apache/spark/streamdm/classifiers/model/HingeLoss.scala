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

package org.apache.spark.streamdm.classifiers.model

/**
 * Implementation of the squared loss function.
 */

class HingeLoss extends Loss with Serializable {
  /** Computes the value of the loss function
   * @param value the label against which the loss is computed   
   * @param dot the dot product of the linear model and the instance
   * @return the loss value 
   */
  def loss(label: Double, dot: Double): Double = {
    val l = if (label==0) -1.0 else 1
    val v = 1.0 - l*dot
    if (v<0) 0 else v
  }

  /** Computes the value of the gradient function
   * @param value the label against which the gradient is computed   
   * @param dot the dot product of the linear model and the instance
   * @return the gradient value 
   */
  def gradient(label: Double, dot: Double): Double = {
    val l = if (label==0) -1.0 else 1
    val d = l*dot
    if (d<1) -l else 0
  }

  /** Computes the binary prediction based on a dot prodcut
   * @param dot the dot product of the linear model and the instance
   * @return the predicted binary class
   */
  def predict(dot: Double): Double =
    if (dot>=0) 1 else 0
}
