package com.lloyd.triggerise.data

import com.lloyd.triggerise.data.models.cards.Cards
import com.lloyd.triggerise.data.models.sets.Sets
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestCards(): Flow<Resource<Cards>>
    suspend fun requestSets(): Flow<Resource<Sets>>
}
