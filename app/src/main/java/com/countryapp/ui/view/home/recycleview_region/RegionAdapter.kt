package com.countryapp.ui.view.home.recycleview_region

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.R

class RegionAdapter(private val regions: List<Region>, private val onItemSelected: (String) -> Unit) :
    RecyclerView.Adapter<RegionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_region, parent, false)
        return RegionViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.render(regions[position], onItemSelected)
    }

    override fun getItemCount() = regions.size

}