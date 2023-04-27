package com.countryapp.ui.domain.model

import com.countryapp.data.model.CountryModel

data class CountryItem(
    val name: CountryName,
    val independent: Boolean,
    val borders: List<String>?,
    val region: String,
    val capital: List<String>?,
    val subRegion: String?,
    val latlng: String?,
    val flag: Flag?

)

data class CountryName(
    val officialName: String
)

data class Flag(
    val officialName: String
)

fun CountryModel.toDomain() = CountryItem(name, independent, borders, region, capital, subRegion, latlng, flag)