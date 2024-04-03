package com.axondragonscale.jest.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axondragonscale.jest.model.Category
import com.axondragonscale.jest.model.Flags
import com.axondragonscale.jest.model.JokeType
import com.axondragonscale.jest.model.Language

/**
 * Created by Ronak Harkhani on 03/04/24
 */
@Entity(
    tableName = "joke",
    indices = [],
)
data class JokeEntity(
    @PrimaryKey val id: Int,
    val lang: Language,
    val category: Category,
    val type: JokeType,
    @Embedded val flags: Flags,
    val safe: Boolean,
    val joke: String?,
    val setup: String?,
    val delivery: String?
)
