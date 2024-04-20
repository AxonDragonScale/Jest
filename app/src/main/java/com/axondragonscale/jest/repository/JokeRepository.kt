package com.axondragonscale.jest.repository

import com.axondragonscale.jest.database.JestDatabaseClient
import com.axondragonscale.jest.database.entity.JokeEntity
import com.axondragonscale.jest.database.mapper.toModel
import com.axondragonscale.jest.model.IJoke
import com.axondragonscale.jest.model.isOld
import com.axondragonscale.jest.network.JokeApiClient
import com.axondragonscale.jest.network.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 02/04/24
 */
@Singleton
class JokeRepository @Inject constructor(
    private val prefsRepository: PrefsRepository,
    private val apiClient: JokeApiClient,
    private val dbClient: JestDatabaseClient,
) {

    suspend fun getCurrentJoke(): IJoke {
        var joke = dbClient.getCurrentJoke()?.toModel()
        if (joke == null || joke.isOld())
            joke = getNewJoke()
        return joke
    }

    suspend fun getNewJoke(): IJoke {
        val categories = prefsRepository.jokeCategoriesFlow
            .firstOrNull()
            .toCommaSeparatedString()
            .ifNullOrBlank("Any")!!

        val jokeTypes = prefsRepository.jokeTypesFlow
            .firstOrNull()
            .toCommaSeparatedString()
            .ifNullOrBlank(null)

        val blacklistFlags = prefsRepository.blacklistFlagsFlow
            .firstOrNull()
            .toCommaSeparatedString()
            .ifNullOrBlank(null)

        var retryCount = 5
        var joke: JokeEntity
        do {
            joke = apiClient.getJoke(
                category = categories,
                type = jokeTypes,
                blacklistFlags = blacklistFlags,
            ).toEntity()
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

    private fun <E : Enum<E>> List<Enum<E>>?.toCommaSeparatedString() =
        this?.joinToString(",") { it.name }

    private fun String?.ifNullOrBlank(default: String?) =
        if (this.isNullOrBlank()) default else this

}
