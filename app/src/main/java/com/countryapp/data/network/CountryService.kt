package com.countryapp.data.network

import com.countryapp.data.model.CountryModel
import com.countryapp.data.model.DetailCountryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryService @Inject constructor(private val api: ApiService) {

    suspend fun getCountriesByContinent(continent: String) : List<CountryModel>{
        return withContext(Dispatchers.IO){
            val response = api.getCountriesFromContinent(continent)
            response.body()!!
        }
    }
    suspend fun getCountriesBySubContinent(subContinent: String) : List<CountryModel>{
        return withContext(Dispatchers.IO){
            val response = api.getCountriesFromSubContinent(subContinent)
            response.body()!!
        }
    }
    suspend fun getCountryByID(id: String): List<DetailCountryModel>{
        return withContext(Dispatchers.IO){
            val response = api.getCountryByID(id)
            response.body()!!
        }
    }

}