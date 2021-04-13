package com.lloyd.triggerise.data.remote

import com.lloyd.triggerise.data.models.cards.Cards
import com.lloyd.triggerise.data.Resource
import com.lloyd.triggerise.data.models.sets.Sets

internal interface RemoteDataSource {
    suspend fun requestCards(): Resource<Cards>
    suspend fun requestSets(): Resource<Sets>
}
