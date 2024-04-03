package com.axondragonscale.jest.network.serialization

import com.axondragonscale.jest.network.response.IJokeApiResponse
import com.axondragonscale.jest.network.response.MultiJokeApiResponse
import com.axondragonscale.jest.network.response.SingleJokeApiResponse
import com.axondragonscale.jest.network.response.TwoPartJokeApiResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Created by Ronak Harkhani on 03/04/24
 */
object JokeApiResponseSerializer :
    JsonContentPolymorphicSerializer<IJokeApiResponse>(IJokeApiResponse::class) {

    override fun selectDeserializer(
        element: JsonElement
    ): DeserializationStrategy<IJokeApiResponse> {
        val jsonObject = element.jsonObject

        if (jsonObject.contains("amount") && jsonObject.contains("jokes"))
            return  MultiJokeApiResponse.serializer()

        val type = jsonObject.get("type")?.jsonPrimitive?.content
        return when (type) {
            "single" -> SingleJokeApiResponse.serializer()
            "twopart" -> TwoPartJokeApiResponse.serializer()
            else -> throw IllegalArgumentException("Type $type is not supported")
        }

    }

}
