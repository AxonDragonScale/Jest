package com.axondragonscale.jest.network.serialization

import com.axondragonscale.jest.network.response.IJoke
import com.axondragonscale.jest.network.response.SingleJoke
import com.axondragonscale.jest.network.response.TwoPartJoke
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Created by Ronak Harkhani on 03/04/24
 */
object JokeSerializer: JsonContentPolymorphicSerializer<IJoke>(IJoke::class) {

    override fun selectDeserializer(
        element: JsonElement
    ): DeserializationStrategy<IJoke> {
        val jsonObject = element.jsonObject
        val type = jsonObject.get("type")?.jsonPrimitive?.content
        return when (type) {
            "single" -> SingleJoke.serializer()
            "twopart" -> TwoPartJoke.serializer()
            else -> throw IllegalArgumentException("Type $type is not supported.")
        }
    }

}
