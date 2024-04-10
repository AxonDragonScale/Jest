package com.axondragonscale.jest.repository

import com.axondragonscale.jest.database.JestDatabaseClient
import com.axondragonscale.jest.database.mapper.toEntity
import com.axondragonscale.jest.model.IJoke
import com.axondragonscale.jest.network.JokeApiClient
import com.axondragonscale.jest.network.mapper.toModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 02/04/24
 */
@Singleton
class JokeRepository @Inject constructor(
    private val apiClient: JokeApiClient,
    private val dbClient: JestDatabaseClient,
) {

    suspend fun getNewJoke(): IJoke {
        return apiClient.getJoke().toModel()
    }

    suspend fun addToFavorites(joke: IJoke) {
        dbClient.insertJoke(joke.toEntity())
    }

    suspend fun removeFromFavorites(joke: IJoke) {
        dbClient.deleteJoke(joke.id)
    }
}
