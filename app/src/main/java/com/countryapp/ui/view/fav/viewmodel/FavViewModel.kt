package com.countryapp.ui.view.fav.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.countryapp.data.database.entities.toDatabase
import com.countryapp.ui.domain.GetAllCountries
import com.countryapp.ui.domain.GetCountryCodes
import com.countryapp.ui.domain.InsertFavCountry
import com.countryapp.ui.domain.RemoveFavCountry
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.view.home.HomeActivity.Companion.EMAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val getCountries: GetAllCountries,
    private val getCountryCodes: GetCountryCodes,
    private val insertCountry: InsertFavCountry,
    private val removeCountry: RemoveFavCountry
) : ViewModel() {

    val countryData = MutableLiveData<List<CountryItem>?>()

    fun getAllCountries() {
        viewModelScope.launch {
            val result = getCountries()
            val resultDB = getCountryCodes()
            val finalResult = result.filter { country ->
                var fav = false
                resultDB.forEach{
                    if(country.code == it.code){
                        fav = true
                        country.fav = true
                    }

                }
                fav
            }
            countryData.postValue(finalResult)
        }
    }

    fun insertFavCountry (country: CountryItem){
        viewModelScope.launch {
            insertCountry(country.toDatabase(EMAIL))
        }
    }
    fun removeFavCountry (country: CountryItem){
        viewModelScope.launch {
            removeCountry(country.toDatabase(EMAIL))
        }
    }
}