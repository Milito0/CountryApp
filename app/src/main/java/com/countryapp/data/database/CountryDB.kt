package com.countryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.countryapp.data.database.dao.FavCountryDAO
import com.countryapp.data.database.entities.FavCountryEntity

@Database(entities = [FavCountryEntity::class], version = 1)
abstract class CountryDB: RoomDatabase() {

    abstract fun getFavCountry(): FavCountryDAO
}