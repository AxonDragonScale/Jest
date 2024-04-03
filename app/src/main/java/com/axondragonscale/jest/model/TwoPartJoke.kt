package com.axondragonscale.jest.model

import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 03/04/24
 *
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
    override val delivery: String
): ITwoPartJoke
