package com.lloyd.triggerise.data.models.cards

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ForeignName(
    @Json(name = "flavor") val flavor: String? = null,
    @Json(name = "imageUrl") val imageUrl: String = "",
    @Json(name = "language") val language: String ="",
    @Json(name = "multiverseid") val multiverseid: Int=0,
    @Json(name = "name") val name: String="",
    @Json(name = "text") val text: String="",
    @Json(name = "type") val type: String=""
) : Parcelable