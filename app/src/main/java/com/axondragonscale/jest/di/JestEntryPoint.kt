package com.axondragonscale.jest.di

import com.axondragonscale.jest.repository.JokeRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Ronak Harkhani on 20/04/24
 */

@InstallIn(SingletonComponent::class)
@EntryPoint
interface JestEntryPoint {
    fun provideJokeRepository(): JokeRepository
}
