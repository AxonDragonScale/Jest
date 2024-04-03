package com.axondragonscale.jest.network

import com.axondragonscale.jest.network.response.IJokeApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ronak Harkhani on 02/04/24
 */
interface JokeApi {

    @GET("joke/{category}")
    suspend fun getJoke(
        @Path("category") category: String? = "Any",
        @Query("amount") amount: Int? = null,
        @Query("lang") lang: String? = null,
        @Query("blacklistFlags") blacklistFlags: String? = null,
        @Query("type") type: String? = null,
    ): IJokeApiResponse

}
