package com.countryapp.ui.home.recycleview

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.databinding.ItemRegionBinding

class RegionViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemRegionBinding.bind(view)

    fun render(region: Region, onItemSelected: (String) -> Unit){
        binding.tvName.text = region.name
        binding.tvCountryNumber.text = region.countries.toString()
        binding.cvCountry.setCardBackgroundColor(ContextCompat.getColor(binding.cvCountry.context, region.color))

        binding.cvCountry.setOnClickListener(){ onItemSelected(region.codeName) }
    }

}