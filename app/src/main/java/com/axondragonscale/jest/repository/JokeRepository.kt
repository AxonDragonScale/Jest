package com.axondragonscale.jest.repository

import com.axondragonscale.jest.database.JestDatabaseClient
import com.axondragonscale.jest.database.entity.JokeEntity
import com.axondragonscale.jest.database.mapper.toModel
import com.axondragonscale.jest.model.IJoke
import com.axondragonscale.jest.network.JokeApiClient
import com.axondragonscale.jest.network.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    suspend fun getCurrentJoke(): IJoke {
        return dbClient.getCurrentJoke()?.toModel() ?: getNewJoke()
    }

    suspend fun getNewJoke(): IJoke {
        var retryCount = 5
        var joke: JokeEntity
        do {
            joke = apiClient.getJoke().toEntity()
            val inserted = dbClient.insertJoke(joke)
        } while (inserted == -1L && retryCount-- > 0)
        return joke.toModel()
    }

    fun getAllJokes(): Flow<List<IJoke>> {
        return dbClient.getAllJokes().map { it.map { it.toModel() } }
    }

    fun getFavoriteJokes(): Flow<List<IJoke>> {
        return dbClient.getFavoriteJokes().map { it.map { it.toModel() } }
    }

    suspend fun addToFavorites(jokeId: Int) {
        dbClient.updateFavoriteFlag(jokeId, favorite = true)
    }

    suspend fun removeFromFavorites(jokeId: Int) {
        dbClient.updateFavoriteFlag(jokeId, favorite = false)
    }
}
