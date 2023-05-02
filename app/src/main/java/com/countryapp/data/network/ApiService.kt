package com.countryapp.data.network

import com.countryapp.data.model.CountryModel
import com.countryapp.data.model.DetailCountryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("region/{continent}")
    suspend fun getCountriesFromContinent(@Path("continent") continent: String) : Response<List<CountryModel>>

    @GET("subregion/{subContinent}")
    suspend fun getCountriesFromSubContinent(@Path("subContinent") subContinent: String) : Response<List<CountryModel>>

    @GET("alpha/{id}")
    suspend fun getCountryByID(@Path("id") id: String): Response<List<DetailCountryModel>>

    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): Response<List<CountryModel>>

    @GET("independent?status=true")
    suspend fun getAllCountries(): Response<List<CountryModel>>

}