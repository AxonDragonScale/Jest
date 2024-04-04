package com.axondragonscale.jest.network.response

import com.axondragonscale.jest.model.Category
import com.axondragonscale.jest.model.Flags
import com.axondragonscale.jest.model.IOnePartJoke
import com.axondragonscale.jest.model.ITwoPartJoke
import com.axondragonscale.jest.model.JokeType
import com.axondragonscale.jest.model.Language
import com.axondragonscale.jest.network.serialization.JokeApiResponseSerializer
import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 02/04/24
 */

@Serializable(with = JokeApiResponseSerializer::class)
sealed interface IJokeApiResponse {
    val error: Boolean
}

@Serializable
data class OnePartJokeApiResponse(
    override val error: Boolean,
    override val id: Int,
    override val lang: Language,
    override val category: Category,
    override val flags: Flags,
    override val safe: Boolean,
    override val type: JokeType,
    override val joke: String,
): IJokeApiResponse, IOnePartJoke

@Serializable
data class TwoPartJokeApiResponse(
    override val error: Boolean,
    override val id: Int,
    override val lang: Language,
    override val category: Category,
    override val flags: Flags,
    override val safe: Boolean,
    override val type: JokeType,
    override val setup: String,
    override val delivery: String,
) : IJokeApiResponse, ITwoPartJoke





