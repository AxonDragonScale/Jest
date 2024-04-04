package com.axondragonscale.jest.database.mapper

import com.axondragonscale.jest.database.entity.JokeEntity
import com.axondragonscale.jest.model.IJoke
import com.axondragonscale.jest.model.IOnePartJoke
import com.axondragonscale.jest.model.ITwoPartJoke
import com.axondragonscale.jest.model.JokeType

/**
 * Created by Ronak Harkhani on 04/04/24
 */

fun JokeEntity.toModel(): IJoke = when (type) {
    JokeType.Single -> TODO()
    JokeType.TwoPart -> TODO()
}

fun IJoke.toEntity(): JokeEntity = when (this) {
    is IOnePartJoke -> TODO()
    is ITwoPartJoke -> TODO()
}
