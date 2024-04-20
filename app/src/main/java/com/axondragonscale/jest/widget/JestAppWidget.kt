package com.axondragonscale.jest.widget

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import com.axondragonscale.jest.di.JestEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.runBlocking

/**
 * Created by Ronak Harkhani on 20/04/24
 */
class JestAppWidget : GlanceAppWidget() {

    companion object {
        val updateCountKey = longPreferencesKey("updateCount")
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val repository = EntryPointAccessors
            .fromApplication<JestEntryPoint>(context)
            .provideJokeRepository()

        provideContent {
            GlanceTheme {
                currentState<Preferences>() // Widget is not updated unless currentState is called
                val joke = runBlocking { repository.getCurrentJoke() }
                JestWidget(joke)
            }
        }
    }

}
