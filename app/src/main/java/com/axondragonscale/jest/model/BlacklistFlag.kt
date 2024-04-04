package com.axondragonscale.jest.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Ronak Harkhani on 03/04/24
 */
@Serializable
enum class BlacklistFlag(val flag: String) {
    @SerialName("nsfw") NSFW("nsfw"),
    @SerialName("religious") Religious("religious"),
    @SerialName("political") Political("political"),
    @SerialName("racist") Racist("racist"),
    @SerialName("sexist") Sexist("sexist"),
    @SerialName("explicit") Explicit("explicit");
}
