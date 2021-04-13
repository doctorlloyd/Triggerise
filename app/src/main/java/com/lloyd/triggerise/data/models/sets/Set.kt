package com.lloyd.triggerise.data.models.sets

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

@JsonClass(generateAdapter = true)
@Parcelize
data class Set(
    @Json(name = "block")val block: String = "",
    @Json(name = "booster")val booster: @RawValue List<Any> = listOf(),
    @Json(name = "code")val code: String = "",
    @Json(name = "name")val name: String="",
    @Json(name = "onlineOnly")val onlineOnly: Boolean=false,
    @Json(name = "releaseDate")val releaseDate: String="",
    @Json(name = "type")val type: String=""
) : Parcelable