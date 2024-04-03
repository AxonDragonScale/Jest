package com.axondragonscale.jest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axondragonscale.jest.database.JestDatabase.Companion.DB_VERSION

/**
 * Created by Ronak Harkhani on 03/04/24
 */
@Database(
    entities = [

    ],
    views = [],
    version = DB_VERSION,
    exportSchema = true,
    autoMigrations = []
)
internal abstract class JestDatabase : RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "JestDatabase"

        fun create(appContext: Context): JestDatabase =
            Room.databaseBuilder(
                context = appContext,
                klass = JestDatabase::class.java,
                name = DB_NAME
            ).build()

    }


}
