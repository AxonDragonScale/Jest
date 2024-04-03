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
    val safe: Boolean
}

interface ISingleJoke : IJoke {
    val joke: String
}

/**
 * Joke where the type is `single`
 */
@Serializable
data class OnePartJoke(
    override val id: Int,
    override val lang: String,
    override val category: String,
    override val type: String,
    override val flags: Flags,
    override val joke: String,
    override val safe: Boolean,
): ISingleJoke

interface ITwoPartJoke : IJoke {
    val setup: String
    val delivery: String
}

/**
 * Joke where type is `twopart`
 */
@Serializable
data class TwoPartJoke(
    override val id: Int,
    override val lang: String,
    override val category: String,
    override val type: String,
    override val flags: Flags,
    override val setup: String,
    override val delivery: String,
    override val safe: Boolean,
): ITwoPartJoke
