package com.countryapp.ui.view.fav.recyclerview_favcountry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.R
import com.countryapp.ui.domain.model.CountryItem

class FavCountryAdapter(
    private var countryList: List<CountryItem> = emptyList(),
    private var onItemSelected: (CountryItem) -> Unit,
    private var addFavCountry: (CountryItem) -> Unit,
    private var removeFavCountry: (CountryItem) -> Unit
) : RecyclerView.Adapter<FavCountryViewHolder>() {

    fun updateList(countries: List<CountryItem>) {
        countryList = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCountryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favcountry, parent, false)
        return FavCountryViewHolder(view)
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: FavCountryViewHolder, position: Int) {
        holder.render(countryList[position], onItemSelected, addFavCountry, removeFavCountry)
    }

}