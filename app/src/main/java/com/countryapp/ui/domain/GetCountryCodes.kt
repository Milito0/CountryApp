package com.countryapp.ui.domain

import com.countryapp.data.CountryRepository
import com.countryapp.data.database.entities.FavCountryEntity
import javax.inject.Inject

class GetCountryCodes @Inject constructor(
    private val repository: CountryRepository
) {

    suspend operator fun invoke(): List<FavCountryEntity>{
        return repository.getCountryCodesFromDB()
    }
}