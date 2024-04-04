package com.axondragonscale.jest.database

import android.content.Context
import com.axondragonscale.jest.database.dao.JokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 03/04/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideJestDatabase(@ApplicationContext context: Context): JestDatabase =
        JestDatabase.create(context)

    @Singleton
    @Provides
    fun provideJokeDao(database: JestDatabase): JokeDao =
        database.getJokeDao()

}
