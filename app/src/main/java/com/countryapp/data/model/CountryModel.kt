package com.countryapp.data.model

import com.countryapp.ui.domain.model.CountryName
import com.countryapp.ui.domain.model.Flag
import com.google.gson.annotations.SerializedName


data class CountryModel (
    @SerializedName("name") val name: CountryName,
    @SerializedName("independent") val independent: Boolean,
    @SerializedName("borders") val borders: List<String>?,
    @SerializedName("region") val region: String,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("subregion") val subRegion: String?,
    @SerializedName("latlng") val latlng: String?,
    @SerializedName("flag") val flag: Flag?

    )


