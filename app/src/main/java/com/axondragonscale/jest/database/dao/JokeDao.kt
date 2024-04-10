package com.axondragonscale.jest.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.axondragonscale.jest.database.entity.JokeEntity

/**
 * Created by Ronak Harkhani on 04/04/24
 */
@Dao
interface JokeDao {

    @Upsert
    suspend fun upsert(jokes: List<JokeEntity>)

    @Query("SELECT * FROM joke")
    suspend fun getJokes(): List<JokeEntity>

    @Query("DELETE FROM joke WHERE id = :id")
    suspend fun deleteJoke(id: Int)

    @Delete
    suspend fun deleteJoke(joke: JokeEntity)

    @Query("DELETE FROM joke WHERE 1")
    suspend fun deleteAllJokes()

}
