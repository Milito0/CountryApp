package com.countryapp.ui.view.home.recycleview_subregion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.countryapp.R

class SubRegionAdapter(private val subRegions: List<SubRegion>, private val onItemSelected: (String) -> Unit) :
    RecyclerView.Adapter<SubRegionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubRegionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subregion, parent, false)
        return SubRegionViewHolder(view)
    }

    override fun getItemCount() = subRegions.size

    override fun onBindViewHolder(holder: SubRegionViewHolder, position: Int) {

        holder.render(subRegions[position], onItemSelected)


    }
}