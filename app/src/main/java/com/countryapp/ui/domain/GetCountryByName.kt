package com.countryapp.ui.domain

import com.countryapp.data.CountryRepository
import com.countryapp.ui.domain.model.CountryItem
import javax.inject.Inject

class GetCountryByName @Inject constructor(
    private val repository: CountryRepository
) {

    suspend operator fun invoke(name: String): List<CountryItem>?{
        return repository.getCountryByName(name)
    }
}