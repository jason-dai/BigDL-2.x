/*
 * Copyright 2016 The BigDL Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intel.analytics.bigdl.dllib.nn.ops

import com.intel.analytics.bigdl.dllib.nn.abstractnn.Activity
import com.intel.analytics.bigdl.dllib.tensor.TensorNumericMath.TensorNumeric
import com.intel.analytics.bigdl.dllib.tensor.{BooleanType, Tensor}

import scala.reflect.ClassTag

class LogicalNot[T: ClassTag]()
  (implicit ev: TensorNumeric[T]) extends Operation[Tensor[Boolean], Tensor[Boolean], T] {

  output = Activity.allocate[Tensor[Boolean], Boolean]()
  gradInput = Activity.allocate[Tensor[Boolean], Boolean]()

  override def updateOutput(input: Tensor[Boolean]): Tensor[Boolean] = {
    output.resizeAs(input).copy(input)
    output.apply1(!_)
    output
  }
}

object LogicalNot {
  def apply[T: ClassTag]()(implicit ev: TensorNumeric[T]): Operation[Activity, Activity, T]
  = ModuleToOperation[T](new LogicalNot())
}
