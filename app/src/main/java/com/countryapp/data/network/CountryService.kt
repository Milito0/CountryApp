package com.countryapp.data.network

import com.countryapp.data.model.CountryModel
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

}