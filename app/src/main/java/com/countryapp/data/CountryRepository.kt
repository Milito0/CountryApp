package com.countryapp.data

import com.countryapp.data.database.dao.FavCountryDAO
import com.countryapp.data.database.entities.FavCountryEntity
import com.countryapp.data.network.CountryService
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.domain.model.DetailCountryItem
import com.countryapp.ui.domain.model.toDomain
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api: CountryService,
    private val favCountryDAO: FavCountryDAO
){

    suspend fun getContinentCountries(continent:String): List<CountryItem>?{
        val x = api.getCountriesByContinent(continent)
        if(x.isNullOrEmpty()){
            return null
        }
        return api.getCountriesByContinent(continent)?.map { it.toDomain() }
    }
    suspend fun getSubContinentCountries(subContinent:String): List<CountryItem>?{
        val x = api.getCountriesBySubContinent(subContinent)
        if(x.isNullOrEmpty()){
            return null
        }
        return api.getCountriesBySubContinent(subContinent)?.map { it.toDomain() }
    }

    suspend fun getCountryDetail(id: String): List<DetailCountryItem>{
        return api.getCountryByID(id).map {it.toDomain()}
    }

    suspend fun getCountryByName(name: String): List<CountryItem>?{
        val x = api.getCountryByName(name)
        if(x.isNullOrEmpty()){
            return null
        }
        return api.getCountryByName(name)?.map { it.toDomain() }
    }

    suspend fun getCountryCodesFromDB(): List<FavCountryEntity>{
        return favCountryDAO.getAllFavCountries()
    }

    suspend fun getAllCountries(): List<CountryItem>{
        return api.getAllCountries().map { it.toDomain() }
    }

    suspend fun insertCountry(favCountry: FavCountryEntity){
        favCountryDAO.insertFavCountry(favCountry)
    }
    suspend fun removeCountry(favCountry: FavCountryEntity){
        favCountryDAO.removeFavCountry(favCountry.code!!)
    }



}