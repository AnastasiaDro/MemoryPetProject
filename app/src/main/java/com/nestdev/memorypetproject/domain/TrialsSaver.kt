package com.nestdev.memorypetproject.domain

import com.nestdev.memorypetproject.data.roomDatabase.TrialsTable

/**
 * repository class for domain-data interaction
 *
 * @author Anastasia Drogunova
 */

interface TrialsSaver {
    suspend fun save(trialsTable: TrialsTable)
}
