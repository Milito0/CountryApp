package com.countryapp.ui.domain.model

import com.countryapp.data.model.CountryModel

data class CountryItem(
    val name: CountryName,
    val independent: Boolean,
    val borders: List<String>?,
    val region: String,
    val capital: List<String>?,
    val subRegion: String?,
    val latlng: List<Float>?,
    val flags: Flag?,
    val code: String?,
    var fav: Boolean = false
)

data class CountryName(
    val common: String
)

data class Flag(
    val png: String
)

fun CountryModel.toDomain() = CountryItem(name, independent, borders, region, capital, subRegion, latlng, flags, code)