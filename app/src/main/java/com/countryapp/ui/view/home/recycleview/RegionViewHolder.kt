package com.countryapp.ui.view.home.recycleview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.databinding.ItemRegionBinding

class RegionViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemRegionBinding.bind(view)

    fun render(region: Region){
        binding.tvName.text = region.name
    }

}