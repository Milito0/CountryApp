package com.countryapp.ui.view.search.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countryapp.ui.domain.GetCountriesByContinent
import com.countryapp.ui.domain.model.CountryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCountriesByContinent: GetCountriesByContinent
) : ViewModel () {
    val countryData = MutableLiveData<List<CountryItem>>()

    fun getCountries(continent: String){
        viewModelScope.launch {
            val result = getCountriesByContinent(continent)
            Log.i("milito", result.toString())
            val filterResult = result.filter { it.independent }
            countryData.postValue(filterResult)
        }
    }
}