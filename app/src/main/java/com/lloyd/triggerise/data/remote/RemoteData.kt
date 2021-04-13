package com.lloyd.triggerise.data.remote

import com.lloyd.triggerise.data.Resource
import com.lloyd.triggerise.data.error.NETWORK_ERROR
import com.lloyd.triggerise.data.error.NO_INTERNET_CONNECTION
import com.lloyd.triggerise.data.models.cards.Card
import com.lloyd.triggerise.data.models.cards.Cards
import com.lloyd.triggerise.data.models.sets.Set
import com.lloyd.triggerise.data.models.sets.Sets
import com.lloyd.triggerise.data.remote.service.TriggeriseService
import com.lloyd.triggerise.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) :
    RemoteDataSource {
    override suspend fun requestCards(): Resource<Cards> {
        val triggeriseService = serviceGenerator.createService(TriggeriseService::class.java)
        return when (val response = processCall(triggeriseService::fetchCards)) {
            is List<*> -> {
                Resource.Success(data = Cards(response as List<Card>))
            }
            else -> {
                Resource.Success(data = response as Cards)
            }
        }
    }

    override suspend fun requestSets(): Resource<Sets> {
        val triggeriseService = serviceGenerator.createService(TriggeriseService::class.java)
        return when (val response = processCall(triggeriseService::fetchSets)) {
            is List<*> -> {
                Resource.Success(data = Sets(response as List<Set>))
            }
            else -> {
                Resource.Success(data = response as Sets)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }

        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
