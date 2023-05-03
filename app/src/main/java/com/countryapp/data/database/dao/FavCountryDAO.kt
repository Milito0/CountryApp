package com.countryapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.countryapp.data.database.entities.FavCountryEntity

@Dao
interface FavCountryDAO {

    @Query("SELECT * FROM fav_country_table WHERE email = :email")
    suspend fun getAllFavCountries(email:String): List<FavCountryEntity>

    @Insert
    suspend fun insertFavCountry(favCountryEntity: FavCountryEntity)

    @Query("DELETE FROM fav_country_table WHERE (code = :code AND email = :email)")
    suspend fun removeFavCountry(code: String, email:String)
}