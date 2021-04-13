package com.lloyd.triggerise.data.remote.service

import com.lloyd.triggerise.data.models.cards.Cards
import com.lloyd.triggerise.data.models.sets.Sets
import com.lloyd.triggerise.data.models.sets.Set
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TriggeriseService {
    @GET("cards")
    suspend fun fetchCards(): Response<Cards>

    @GET("sets")
    suspend fun fetchSets(): Response<Sets>

    @GET("sets/{id}")
    suspend fun fetchSet(@Path("id") id: String): Response<Set>
}
