package com.countryapp.ui.domain

import com.countryapp.data.CountryRepository
import com.countryapp.data.model.CountryModel
import javax.inject.Inject

class GetCountriesByContinent @Inject constructor(
    private val repository: CountryRepository
) {

    suspend operator fun invoke(country: String): List<CountryModel>{
        return repository.getContinentCountries(country)
    }
}