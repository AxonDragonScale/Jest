package com.axondragonscale.jest.model

/**
 * Created by Ronak Harkhani on 03/04/24
 */
enum class Language(val code: String) {
    English("en"),
    Czech("cs"),
    German("de"),
    Spanish("es"),
    French("fr"),
    Portuguese("pt");

    companion object {
        fun fromOrdinal(ordinal: Int) = entries[ordinal]
    }
}
