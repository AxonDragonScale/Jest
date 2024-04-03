package com.axondragonscale.jest.model

import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 03/04/24
 *
 * Joke where the type is `single`
 */
@Serializable
data class OnePartJoke(
    override val id: Int,
    override val lang: String,
    override val category: String,
    override val type: String,
    override val flags: Flags,
    override val joke: String
): ISingleJoke
