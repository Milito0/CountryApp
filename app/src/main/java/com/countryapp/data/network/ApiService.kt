package com.countryapp.data.network

import com.countryapp.data.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("region/{continent}?fields=name,region")
    suspend fun getCountriesFromContinent(@Path("continent") continent: String) : Response<List<CountryModel>>
}