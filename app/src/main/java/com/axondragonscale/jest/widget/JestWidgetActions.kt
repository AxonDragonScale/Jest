package com.axondragonscale.jest.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import com.axondragonscale.jest.di.JestEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Ronak Harkhani on 20/04/24
 */

class FavoriteAction: ActionCallback {

    companion object {
        val jokeIdKey = ActionParameters.Key<Int>("jokeId")
        val favoriteKey = ActionParameters.Key<Boolean>("favorite")
    }

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val jokeId = parameters[jokeIdKey] ?: return
        val favorite = parameters[favoriteKey] ?: return
        val repository = EntryPointAccessors
            .fromApplication<JestEntryPoint>(context)
            .provideJokeRepository()
        withContext(Dispatchers.IO) {
            if (favorite) repository.addToFavorites(jokeId)
            else repository.removeFromFavorites(jokeId)

            updateAppWidgetState(context, glanceId) {
                val updateCount = it[JestAppWidget.updateCountKey] ?: 0
                it[JestAppWidget.updateCountKey] = updateCount + 1
            }
        }
        JestAppWidget().update(context, glanceId)
    }

}


class ShuffleAction: ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val repository = EntryPointAccessors
            .fromApplication<JestEntryPoint>(context)
            .provideJokeRepository()
        withContext(Dispatchers.IO) {
            repository.getNewJoke()
            updateAppWidgetState(context, glanceId) {
                val updateCount = it[JestAppWidget.updateCountKey] ?: 0
                it[JestAppWidget.updateCountKey] = updateCount + 1
            }
        }
        JestAppWidget().update(context, glanceId)
    }

}
