package com.axondragonscale.jest.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 02/04/24
 */

const val BASE_URL = "https://v2.jokeapi.dev/"

@Singleton
class JokeApiClient @Inject constructor(
    @ApplicationContext context: Context,
    private val json: Json,
) : JokeApi by Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    )
    .addConverterFactory(
        json.asConverterFactory("application/json".toMediaType())
    )
    .build()
    .create(JokeApi::class.java)
