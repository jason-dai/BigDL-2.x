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

package com.intel.analytics.bigdl.dllib.keras.layers

import com.intel.analytics.bigdl.dllib.nn.{AddConstant => BAddConstant}
import com.intel.analytics.bigdl.dllib.keras.layers.{AddConstant => ZAddConstant}
import com.intel.analytics.bigdl.dllib.tensor.Tensor
import com.intel.analytics.bigdl.dllib.utils.Shape
import com.intel.analytics.bigdl.dllib.keras.ZooSpecHelper
import com.intel.analytics.bigdl.dllib.keras.serializer.ModuleSerializationTest


class AddConstantSpec extends ZooSpecHelper {

  "AddConstant 1 Zoo" should "be the same as BigDL" in {
    val blayer = BAddConstant[Float](1)
    val zlayer = ZAddConstant[Float](1, inputShape = Shape(4, 5))
    zlayer.build(Shape(-1, 4, 5))
    zlayer.getOutputShape().toSingle().toArray should be (Array(-1, 4, 5))
    val input = Tensor[Float](Array(3, 4, 5)).rand()
    compareOutputAndGradInput(blayer, zlayer, input)
  }

  "AddConstant -0.4 Zoo" should "be the same as BigDL" in {
    val blayer = BAddConstant[Float](-0.4)
    val zlayer = ZAddConstant[Float](-0.4, inputShape = Shape(4, 8, 8))
    zlayer.build(Shape(-1, 4, 8, 8))
    zlayer.getOutputShape().toSingle().toArray should be (Array(-1, 4, 8, 8))
    val input = Tensor[Float](Array(3, 4, 8, 8)).rand()
    compareOutputAndGradInput(blayer, zlayer, input)
  }

}

class AddConstantSerialTest extends ModuleSerializationTest {
  override def test(): Unit = {
    val layer = ZAddConstant[Float](1, inputShape = Shape(4, 5))
    layer.build(Shape(2, 4, 5))
    val input = Tensor[Float](2, 4, 5).rand()
    runSerializationTest(layer, input)
  }
}
