package com.lloyd.triggerise.data.models.cards

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Ruling(
    @Json(name = "date") val date: String = "",
    @Json(name = "text") val text: String = ""
) : Parcelable