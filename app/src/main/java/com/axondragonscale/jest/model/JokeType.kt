package com.axondragonscale.jest.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 03/04/24
 */
@Serializable
enum class JokeType {
    @SerialName("single") Single,
    @SerialName("twopart") TwoPart;

    companion object {
        fun fromOrdinal(ordinal: Int) = entries[ordinal]
    }
}
