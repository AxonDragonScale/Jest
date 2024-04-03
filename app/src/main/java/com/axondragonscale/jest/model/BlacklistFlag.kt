package com.axondragonscale.jest.model

/**
 * Created by Ronak Harkhani on 03/04/24
 */
enum class BlacklistFlag(val flag: String) {
    NSFW("nsfw"),
    Religious("religious"),
    Political("political"),
    Racist("racist"),
    Sexist("sexist"),
    Explicit("explicit");
}
