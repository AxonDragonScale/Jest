package com.axondragonscale.jest.repository

import com.axondragonscale.jest.network.JokeApiClient
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 02/04/24
 */
@Singleton
class JokeRepository @Inject constructor(
    private val apiClient: JokeApiClient
) {
}
