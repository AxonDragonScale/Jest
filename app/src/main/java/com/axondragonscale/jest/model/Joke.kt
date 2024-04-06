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

fun IJoke.getDisplayText() = when (this) {
    is OnePartJoke -> this.joke
    is TwoPartJoke -> this.setup + "\n" + this.delivery
}

fun IJoke.getFirstLine() = when (this) {
    is OnePartJoke -> this.joke
    is TwoPartJoke -> this.setup
}

fun IJoke.getSecondLine() = when (this) {
    is TwoPartJoke -> this.delivery
    else -> null
}

/**
 * Joke where the type is `single`
 */
@Serializable
data class OnePartJoke(
    override val id: Int,
    override val lang: Language,
    override val category: Category,
    override val flags: Flags,
    override val safe: Boolean,
    override val type: JokeType,
    val joke: String,
): IJoke

/**
 * Joke where type is `twopart`
 */
@Serializable
data class TwoPartJoke(
    override val id: Int,
    override val lang: Language,
    override val category: Category,
    override val safe: Boolean,
    override val flags: Flags,
    override val type: JokeType,
    val setup: String,
    val delivery: String,
): IJoke
