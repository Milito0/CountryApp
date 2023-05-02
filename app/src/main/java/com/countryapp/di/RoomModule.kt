package com.countryapp.di

import android.content.Context
import androidx.room.Room
import com.countryapp.data.database.CountryDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DB_NAME = "CountryDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CountryDB::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideFavCountryDAO(db: CountryDB) = db.getFavCountry()
}