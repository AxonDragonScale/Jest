package com.axondragonscale.jest.repository

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
    private val apiClient: JokeApiClient
) {

    suspend fun getRandomJoke(): IJoke = apiClient.getJoke().toModel()

}
