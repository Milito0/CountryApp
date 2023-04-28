package com.countryapp.ui.view.search.recyclerview_country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.R
import com.countryapp.ui.domain.model.CountryItem

class CountryAdapter (
    private var countryList: List<CountryItem> = emptyList(),
    private var onItemSelected: (CountryItem) -> Unit
) : RecyclerView.Adapter<CountryViewHolder>() {

    fun updateList (countries: List<CountryItem>){
        countryList = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.render(countryList[position], onItemSelected)
    }

}