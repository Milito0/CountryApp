package com.countryapp.ui.view.search.recyclerview_country

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.R
import com.countryapp.databinding.ItemCountryBinding
import com.countryapp.ui.domain.model.CountryItem
import com.squareup.picasso.Picasso

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCountryBinding.bind(view)

    fun render(
        country: CountryItem,
        onItemSelected: (CountryItem) -> Unit,
        addFavCountry: (CountryItem) -> Unit,
        removeFavCountry: (CountryItem) -> Unit
    ) {

        if (country.flags != null) Picasso.get().load(country.flags.png).into(binding.ivCountry)
        if (country.fav) {
            binding.favImg.setImageResource(R.drawable.id_fav_full)
        } else {
            binding.favImg.setImageResource(R.drawable.ic_fav_border)
        }
        binding.tvCountry.text = country.name.common
        binding.tvCapital.text = country.capital!![0]
        binding.tvRegion.text = country.region
        binding.favImg.setOnClickListener {
            if (!country.fav) {
                binding.favImg.setImageResource(R.drawable.id_fav_full)
                addFavCountry(country)
            } else {
                binding.favImg.setImageResource(R.drawable.ic_fav_border)
                removeFavCountry(country)
            }
            country.fav = !country.fav
        }
        binding.cvCountry.setOnClickListener { onItemSelected(country) }
    }
}