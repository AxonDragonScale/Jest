package com.axondragonscale.jest.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.axondragonscale.jest.model.IJoke
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 06/04/24
 */

private const val APP_PREFS_STORE = "app_prefs"
private val Context.appPrefs: DataStore<Preferences> by preferencesDataStore(APP_PREFS_STORE)

@Singleton
class AppPrefsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json,
) {

    private val appPrefs = context.appPrefs

    private val currentJokeKey = stringPreferencesKey("currentJoke")
    private val currentJokeFlow: Flow<IJoke?> = appPrefs.data.map { prefs ->
        val joke = prefs[currentJokeKey] ?: return@map null
        json.decodeFromString<IJoke>(joke)
    }

    suspend fun getCurrentJoke(): IJoke? {
        return currentJokeFlow.firstOrNull()
    }

    suspend fun setCurrentJoke(joke: IJoke) {
        appPrefs.edit { prefs ->
            prefs[currentJokeKey] = json.encodeToString(joke)
        }
    }
}
