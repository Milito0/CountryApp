package com.countryapp.ui.view.search.recyclerview_country

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.databinding.ItemCountryBinding
import com.countryapp.ui.domain.model.CountryItem
import com.squareup.picasso.Picasso

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCountryBinding.bind(view)

    fun render(country: CountryItem) {

        if (country.flags != null) Picasso.get().load(country.flags.png).into(binding.ivCountry)
        binding.tvCountry.text = country.name.common
        binding.tvCapital.text = country.capital!![0]
        binding.tvRegion.text = country.region

    }
}