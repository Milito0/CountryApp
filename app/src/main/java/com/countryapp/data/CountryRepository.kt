package com.countryapp.data

import com.countryapp.data.model.CountryModel
import com.countryapp.data.network.CountryService
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api: CountryService
){

    suspend fun getContinentCountries(continent:String): List<CountryModel>{
        return api.getCountriesByContinent(continent)
    }

}