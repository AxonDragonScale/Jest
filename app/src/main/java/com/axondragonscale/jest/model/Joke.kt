package com.axondragonscale.jest.model

import com.axondragonscale.jest.model.serialization.JokeSerializer
import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 02/04/24
 */
@Serializable(with = JokeSerializer::class)
sealed interface IJoke {
    val id: Int
    val lang: Language
    val category: Category
    val type: JokeType
    val flags: Flags
    val safe: Boolean
}

interface IOnePartJoke : IJoke {
    val joke: String
}

/**
 * Joke where the type is `single`
 */
@Serializable
data class OnePartJoke(
    override val id: Int,
    override val lang: Language,
    override val category: Category,
    override val type: JokeType,
    override val flags: Flags,
    override val joke: String,
    override val safe: Boolean,
): IOnePartJoke

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
    override val lang: Language,
    override val category: Category,
    override val type: JokeType,
    override val flags: Flags,
    override val setup: String,
    override val delivery: String,
    override val safe: Boolean,
): ITwoPartJoke
