package com.axondragonscale.jest.network.response

import com.axondragonscale.jest.model.Flags
import com.axondragonscale.jest.model.ISingleJoke
import com.axondragonscale.jest.model.ITwoPartJoke
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
data class SingleJokeApiResponse(
    override val error: Boolean,
    override val id: Int,
    override val lang: String,
    override val category: String,
    override val flags: Flags,
    override val safe: Boolean,
    override val type: String,
    override val joke: String,
): IJokeApiResponse, ISingleJoke

@Serializable
data class TwoPartJokeApiResponse(
    override val error: Boolean,
    override val id: Int,
    override val lang: String,
    override val category: String,
    override val flags: Flags,
    override val safe: Boolean,
    override val type: String,
    override val setup: String,
    override val delivery: String,
) : IJokeApiResponse, ITwoPartJoke





