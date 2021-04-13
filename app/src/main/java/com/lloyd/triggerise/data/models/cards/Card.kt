package com.lloyd.triggerise.data.models.cards

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class Card(
    @Json(name = "artist") val artist: String = "",
    @Json(name = "cmc") val cmc: Double = 0.0,
    @Json(name = "colorIdentity") val colorIdentity: List<String> = listOf(),
    @Json(name = "colors") val colors: List<String> = listOf(),
    @Json(name = "flavor") val flavor: String = "",
    @Json(name = "foreignNames") val foreignNames: List<ForeignName> = listOf(),
    @Json(name = "id") val id: String = "",
    @Json(name = "imageUrl") val imageUrl: String = "",
    @Json(name = "layout") val layout: String = "",
    @Json(name = "legalities") val legalities: List<Legality> = listOf(),
    @Json(name = "manaCost") val manaCost: String = "",
    @Json(name = "multiverseid") val multiverseid: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "number") val number: String = "",
    @Json(name = "originalText") val originalText: String = "",
    @Json(name = "originalType") val originalType: String = "",
    @Json(name = "power") val power: String = "",
    @Json(name = "printings") val printings: List<String> = listOf(),
    @Json(name = "rarity") val rarity: String = "",
    @Json(name = "rulings") val rulings: List<Ruling> = listOf(),
    @Json(name = "set") val `set`: String = "",
    @Json(name = "setName") val setName: String = "",
    @Json(name = "subtypes") val subtypes: List<String> = listOf(),
    @Json(name = "supertypes") val supertypes: List<String> = listOf(),
    @Json(name = "text") val text: String = "",
    @Json(name = "toughness") val toughness: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "types") val types: List<String> = listOf(),
    @Json(name = "variations") val variations: List<String> = listOf()
) : Parcelable