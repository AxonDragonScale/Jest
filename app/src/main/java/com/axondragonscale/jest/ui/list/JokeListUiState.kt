package com.axondragonscale.jest.ui.list

import androidx.paging.PagingData
import com.axondragonscale.jest.model.IJoke

/**
 * Created by Ronak Harkhani on 07/04/24
 */
data class JokeListUiState(
    val jokes: List<IJoke>,
    val paginatedJokes: PagingData<IJoke>? = null,
)
