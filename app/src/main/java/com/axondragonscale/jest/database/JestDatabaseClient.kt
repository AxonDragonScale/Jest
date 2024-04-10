package com.axondragonscale.jest.database

import com.axondragonscale.jest.database.dao.JokeDao
import com.axondragonscale.jest.database.entity.JokeEntity
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 09/04/24
 */
class JestDatabaseClient @Inject constructor(
    private val dao: JokeDao
) {

    suspend fun insertJoke(joke: JokeEntity) {
        dao.upsert(listOf(joke))
    }

    suspend fun deleteJoke(jokeId: Int) {
        dao.deleteJoke(jokeId)
    }

}
