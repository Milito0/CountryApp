package com.countryapp.ui.domain

import com.countryapp.data.CountryRepository
import com.countryapp.ui.domain.model.DetailCountryItem
import javax.inject.Inject

class GetCountryByID @Inject constructor(
    private val repository: CountryRepository
) {

    suspend operator fun invoke(id: String): List<DetailCountryItem>{
        return repository.getCountryDetail(id)
    }
}