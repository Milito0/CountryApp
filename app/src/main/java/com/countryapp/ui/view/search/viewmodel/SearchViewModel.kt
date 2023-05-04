package com.countryapp.ui.view.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countryapp.data.database.entities.toDatabase
import com.countryapp.ui.domain.GetCountriesByContinent
import com.countryapp.ui.domain.GetCountryByName
import com.countryapp.ui.domain.GetCountryBySubContinent
import com.countryapp.ui.domain.GetCountryCodes
import com.countryapp.ui.domain.InsertFavCountry
import com.countryapp.ui.domain.RemoveFavCountry
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.view.home.HomeActivity.Companion.EMAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCountriesByContinent: GetCountriesByContinent,
    private val getCountryCodes: GetCountryCodes,
    private val getCountryBySubContinent: GetCountryBySubContinent,
    private val getCountryByName: GetCountryByName,
    private val insertCountry: InsertFavCountry,
    private val removeCountry: RemoveFavCountry
) : ViewModel() {
    val countryData = MutableLiveData<List<CountryItem>?>()

    fun getCountries(continent: String) {
        viewModelScope.launch {
            val result = getCountriesByContinent(continent)
            val resultDB = getCountryCodes()
            val filterResult = result?.filter { it.independent }
            if (!filterResult.isNullOrEmpty()) {
                filterResult.forEach { country ->
                    resultDB.forEach {
                        if (country.code == it.code)
                            country.fav = true
                    }
                }
                countryData.postValue(filterResult.sortedByDescending { it.fav })
            } else
                countryData.postValue(emptyList())

        }
    }

    fun getCountriesSubContinent(subContinent: String) {
        viewModelScope.launch {
            val result = getCountryBySubContinent(subContinent)
            val resultDB = getCountryCodes()
            val filterResult = result?.filter { it.independent }
            if (!filterResult.isNullOrEmpty()){
                filterResult.forEach { country ->
                    resultDB.forEach {
                        if (country.code == it.code)
                            country.fav = true
                    }
                }
                countryData.postValue(filterResult.sortedByDescending { it.fav })
            }
            else
                countryData.postValue(emptyList())
        }
    }

    fun getCountriesByName(name: String) {
        viewModelScope.launch {
            val result = getCountryByName(name)
            val resultDB = getCountryCodes()
            val filterResult = result?.filter { it.independent }
            if (!filterResult.isNullOrEmpty()) {
                filterResult.forEach { country ->
                    resultDB.forEach {
                        if (country.code == it.code)
                            country.fav = true
                    }
                }
                countryData.postValue(filterResult.sortedByDescending { it.fav })
            }
            else
                countryData.postValue(emptyList())
        }
    }

    fun insertFavCountry(country: CountryItem) {
        viewModelScope.launch {
            insertCountry(country.toDatabase(EMAIL))
        }
    }

    fun removeFavCountry(country: CountryItem) {
        viewModelScope.launch {
            removeCountry(country.toDatabase(EMAIL))
        }
    }
}