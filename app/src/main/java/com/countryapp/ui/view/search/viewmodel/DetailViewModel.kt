package com.countryapp.ui.view.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countryapp.data.database.entities.toDatabase
import com.countryapp.ui.domain.GetCountryByID
import com.countryapp.ui.domain.GetCountryCodes
import com.countryapp.ui.domain.InsertFavCountry
import com.countryapp.ui.domain.RemoveFavCountry
import com.countryapp.ui.domain.model.DetailCountryItem
import com.countryapp.ui.view.home.HomeActivity.Companion.EMAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCountriesByID: GetCountryByID,
    private val getCountryCodes: GetCountryCodes,
    private val insertCountry: InsertFavCountry,
    private val removeCountry: RemoveFavCountry

) : ViewModel() {
    val countryInfo = MutableLiveData<DetailCountryItem>()

    fun getCountry(id: String){
        viewModelScope.launch {
            val result = getCountriesByID(id)
            val resultDB = getCountryCodes()
            resultDB.forEach{
                if(it.code == result[0].code) result[0].fav = true
            }
            countryInfo.postValue(result[0])
        }
    }

    fun insertFavCountry (country: DetailCountryItem){
        viewModelScope.launch {
            insertCountry(country.toDatabase(EMAIL))
        }
    }
    fun removeFavCountry (country: DetailCountryItem){
        viewModelScope.launch {
            removeCountry(country.toDatabase(EMAIL))
        }
    }

}