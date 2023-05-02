package com.countryapp.ui.view.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countryapp.ui.domain.GetCountriesByContinent
import com.countryapp.ui.domain.GetCountryByName
import com.countryapp.ui.domain.GetCountryBySubContinent
import com.countryapp.ui.domain.model.CountryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCountriesByContinent: GetCountriesByContinent,
    private val getCountryBySubContinent: GetCountryBySubContinent,
    private val getCountryByName: GetCountryByName
) : ViewModel() {
    val countryData = MutableLiveData<List<CountryItem>>()

    fun getCountries(continent: String) {
        viewModelScope.launch {
            val result = getCountriesByContinent(continent)
            if(!result.isNullOrEmpty()){
                val filterResult = result.filter { it.independent }
                countryData.postValue(filterResult)
            }

        }
    }

    fun getCountriesSubContinent(subContinent: String) {
        viewModelScope.launch {
            val result = getCountryBySubContinent(subContinent)
            if(!result.isNullOrEmpty()){
                val filterResult = result.filter { it.independent }
                countryData.postValue(filterResult)
            }
        }
    }

    fun getCountriesByName(name: String) {
        viewModelScope.launch {
            if (!name.isNullOrEmpty()) {
                val result = getCountryByName(name)
                if (result != null){
                    val filterResult = result.filter { it.independent }
                    countryData.postValue(filterResult)
                }
            }
        }
    }
}