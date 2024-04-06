package com.axondragonscale.jest.network.mapper

import com.axondragonscale.jest.model.JokeType
import com.axondragonscale.jest.model.OnePartJoke
import com.axondragonscale.jest.model.TwoPartJoke
import com.axondragonscale.jest.network.response.JokeApiResponse

/**
 * Created by Ronak Harkhani on 05/04/24
 */

fun JokeApiResponse.toModel() = when (type) {
    JokeType.Single -> OnePartJoke(
        id = id,
        lang = lang,
        category = category,
        flags = flags,
        safe = safe,
        type = type,
        joke = joke!!,
    )
    JokeType.TwoPart -> TwoPartJoke(
        id = id,
        lang = lang,
        category = category,
        flags = flags,
        safe = safe,
        type = type,
        setup = setup!!,
        delivery = delivery!!,
    )
}
