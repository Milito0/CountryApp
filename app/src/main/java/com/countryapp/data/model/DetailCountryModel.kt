package com.countryapp.data.model

import com.countryapp.ui.domain.model.CountryName
import com.countryapp.ui.domain.model.Flag
import com.google.gson.annotations.SerializedName

data class DetailCountryModel (
    @SerializedName("name") val name: CountryName,
    @SerializedName("borders") val borders: List<String>?,
    @SerializedName("region") val region: String,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("subregion") val subRegion: String?,
    @SerializedName("latlng") val latlng: List<Float>,
    @SerializedName("flags") val flags: Flag?,
    @SerializedName("cioc") val code: String?,
    @SerializedName("population") val population: Long
)