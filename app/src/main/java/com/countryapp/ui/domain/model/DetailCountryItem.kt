package com.countryapp.ui.domain.model

import com.countryapp.data.model.DetailCountryModel


data class DetailCountryItem(
    val name: CountryName,
    val borders: List<String>?,
    val region: String,
    val capital: List<String>?,
    val subRegion: String?,
    val latlng: List<Float>,
    val flags: Flag?,
    val code: String?,
    val population: Long,
    var fav: Boolean = false
)


fun DetailCountryModel.toDomain() = DetailCountryItem(name, borders, region, capital, subRegion, latlng, flags, code, population)
