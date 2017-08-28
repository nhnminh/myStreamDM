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

package org.apache.spark.streamdm.classifiers

import com.github.javacliparser.{ClassOption, FloatOption, IntOption}
import org.apache.spark.streamdm._
import org.apache.spark.streamdm.core._
import org.apache.spark.streamdm.classifiers.model._
import org.apache.spark.streaming.dstream._

/** The PerceptronLearner trains a LinearModel which is a perceptron. It
 * currently is implemented as an SGDLearner with a PerceptronLoss function.
 * The lambda learning rate parameter, and the number of features need to be
 * specified in the associated Task configuration file.
 */
class PerceptronLearner extends SGDLearner {

  override val loss = new PerceptronLoss()  
}
