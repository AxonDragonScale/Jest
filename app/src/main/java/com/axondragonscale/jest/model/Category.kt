package com.axondragonscale.jest.model

/**
 * Created by Ronak Harkhani on 03/04/24
 */
enum class Category {
    Any,            // Category will be Any only for a request. Never for a Joke.
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
