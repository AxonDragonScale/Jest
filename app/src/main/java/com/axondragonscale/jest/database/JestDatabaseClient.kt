package com.axondragonscale.jest.database

import com.axondragonscale.jest.database.dao.JokeDao
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 09/04/24
 */
class JestDatabaseClient @Inject constructor(
    private val dao: JokeDao
): JokeDao by dao
