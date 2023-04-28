package com.countryapp.ui.view.search.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countryapp.ui.domain.GetCountryByID
import com.countryapp.ui.domain.model.DetailCountryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCountriesByID: GetCountryByID
) : ViewModel() {
    val countryInfo = MutableLiveData<DetailCountryItem>()

    fun getCountry(id: String){
        viewModelScope.launch {
            val result = getCountriesByID(id)
            Log.i("Milito", result[0].toString())
            countryInfo.postValue(result[0])
        }
    }
}