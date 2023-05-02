package com.countryapp.ui.domain

import com.countryapp.data.CountryRepository
import com.countryapp.data.database.entities.FavCountryEntity
import javax.inject.Inject

class InsertFavCountry @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(country: FavCountryEntity) {
        repository.insertCountry(country)
    }
}