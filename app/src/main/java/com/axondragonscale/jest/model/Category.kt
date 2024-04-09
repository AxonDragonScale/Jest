package com.axondragonscale.jest.model

import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 03/04/24
 */
@Serializable
enum class Category {
    Programming,
    Misc,
    Dark,
    Pun,
    Spooky,
    Christmas;

    companion object {
        fun fromOrdinal(ordinal: Int) = entries[ordinal]
    }
}
