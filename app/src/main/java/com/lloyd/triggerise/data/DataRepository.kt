package com.lloyd.triggerise.data

import com.lloyd.triggerise.data.models.cards.Cards
import com.lloyd.triggerise.data.models.sets.Sets
import com.lloyd.triggerise.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) :
    DataRepositorySource {

    override suspend fun requestCards(): Flow<Resource<Cards>> {
        return flow {
            emit(remoteRepository.requestCards())
        }.flowOn(ioDispatcher)
    }

    override suspend fun requestSets(): Flow<Resource<Sets>> {
        return flow {
            emit(remoteRepository.requestSets())
        }.flowOn(ioDispatcher)
    }
}
