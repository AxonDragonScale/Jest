package com.axondragonscale.jest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axondragonscale.jest.database.entity.JokeEntity

/**
 * Created by Ronak Harkhani on 04/04/24
 */
@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJoke(joke: JokeEntity): Long

    @Query("SELECT * FROM joke ORDER BY timestamp DESC LIMIT 1")
    suspend fun getCurrentJoke(): JokeEntity?

    @Query("UPDATE joke set favorite = :favorite WHERE id = :id")
    suspend fun updateFavoriteFlag(id: Int, favorite: Boolean)

    @Query("SELECT * FROM joke")
    suspend fun getJokes(): List<JokeEntity>

    @Query("DELETE FROM joke WHERE id = :id")
    suspend fun deleteJoke(id: Int)

    @Query("DELETE FROM joke WHERE 1")
    suspend fun deleteAllJokes()

}
