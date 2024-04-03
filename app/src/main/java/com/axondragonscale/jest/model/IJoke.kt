package com.axondragonscale.jest.model

import com.axondragonscale.jest.model.serialization.JokeSerializer
import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 02/04/24
 */
@Serializable(with = JokeSerializer::class)
sealed interface IJoke {
    val id: Int
    val lang: String
    val category: String
    val type: String
    val flags: Flags
}

interface ISingleJoke : IJoke {
    val joke: String
}

interface ITwoPartJoke : IJoke {
    val setup: String
    val delivery: String
}
