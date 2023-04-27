package com.countryapp.ui.home.recycleview_subregion

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.databinding.ItemSubregionBinding

class SubRegionViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSubregionBinding.bind(view)

    fun render(subRegion: SubRegion, onItemSelected: (String) -> Unit){
        binding.tvName.text = subRegion.name
        binding.cvSubContinent.setCardBackgroundColor(ContextCompat.getColor(binding.cvSubContinent.context, subRegion.color))

        binding.cvSubContinent.setOnClickListener{ onItemSelected(subRegion.name) }
    }


}