package com.countryapp.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.countryapp.R
import com.countryapp.databinding.FragmentHomeBinding
import com.countryapp.ui.view.HomeActivity
import com.countryapp.ui.home.recycleview.Region
import com.countryapp.ui.home.recycleview.RegionAdapter

class HomeFragment : Fragment() {

    private val regions = listOf(
        Region("Europa", "Europe", 44, R.color.europe),
        Region("North America", "North America", 23,R.color.north_america),
        Region("South America", "South America", 12,R.color.south_america),
        Region("Asia", "Asia", 48, R.color.asia),
        Region("Oceania", "Oceania", 14, R.color.oceania),
        Region("Africa","Africa", 54, R.color.africa)
    )
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var regionsAdapter: RegionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        regionsAdapter = RegionAdapter(regions, {searchByContinent(it)})
        binding.rvRegion.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL, false)
        binding.rvRegion.adapter = regionsAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun searchByContinent(continent: String){
        val homeActivity = activity as HomeActivity

        homeActivity.changeToSearch(continent)
    }

}