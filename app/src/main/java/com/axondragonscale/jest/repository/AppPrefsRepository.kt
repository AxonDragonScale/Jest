package com.axondragonscale.jest.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 06/04/24
 */

private const val APP_PREFS_STORE = "app_prefs"
private val Context.appPrefs: DataStore<Preferences> by preferencesDataStore(APP_PREFS_STORE)

@Singleton
class AppPrefsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val appPrefs = context.appPrefs

    private val jokeOfTheDayKey = stringPreferencesKey("jokeOfTheDay")
    val jokeOfTheDayFlow = appPrefs.data.map { prefs ->
        prefs[jokeOfTheDayKey]
    }

    suspend fun setJokeOfTheDay(joke: String) {
        appPrefs.edit { prefs ->
            prefs[jokeOfTheDayKey] = joke
        }
    }
}
