package com.countryapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.domain.model.DetailCountryItem

@Entity(tableName = "fav_country_table")
data class FavCountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "code") val code: String?
)

fun CountryItem.toDatabase() = FavCountryEntity(code = code)
fun DetailCountryItem.toDatabase() = FavCountryEntity(code = code)