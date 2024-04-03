package com.axondragonscale.jest.model

/**
 * Created by Ronak Harkhani on 03/04/24
 */
enum class JokeType(val type: String) {
    Single("single"),
    TwoPart("twopart");

    companion object {
        fun fromOrdinal(ordinal: Int) = entries[ordinal]
    }
}
