package com.countryapp.data.model

import com.google.gson.annotations.SerializedName

//data class Respuesta(
//    @SerializedName("") val countryList: List<CountryModel>
//)

data class CountryModel (
    //@SerializedName("name") val name: String,
    @SerializedName("region") val region: String
        )
