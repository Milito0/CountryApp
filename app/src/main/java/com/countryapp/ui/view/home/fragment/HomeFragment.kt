package com.countryapp.ui.view.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.countryapp.R
import com.countryapp.databinding.FragmentHomeBinding
import com.countryapp.ui.view.home.HomeActivity
import com.countryapp.ui.view.home.recycleview_region.Region
import com.countryapp.ui.view.home.recycleview_region.RegionAdapter
import com.countryapp.ui.view.home.recycleview_subregion.SubRegion
import com.countryapp.ui.view.home.recycleview_subregion.SubRegionAdapter

class HomeFragment : Fragment() {

    private lateinit var regions: List<Region>
    private lateinit var subRegions: List<SubRegion>
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var regionsAdapter: RegionAdapter
    private lateinit var subRegionAdapter: SubRegionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        initAdapters()
    }

    private fun initAdapters() {
        // Adapter para el RV de Continentes
        regionsAdapter = RegionAdapter(regions, { searchByContinent(it) })
        binding.rvRegion.setHasFixedSize(true)
        binding.rvRegion.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRegion.adapter = regionsAdapter

        // Adapter para el RV de SubContinentes
        subRegionAdapter = SubRegionAdapter(subRegions, { searchBySubContinent(it) })
        binding.rvSubRegion.setHasFixedSize(true)
        binding.rvSubRegion.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvSubRegion.adapter = subRegionAdapter
    }

    private fun initList() {
        regions = listOf(
            Region("Europe", 44, R.color.europe),
            Region("Americas", 23, R.color.north_america),
            Region("Asia", 48, R.color.asia),
            Region("Oceania", 14, R.color.oceania),
            Region("Africa", 54, R.color.africa)
        )

        subRegions = listOf(
            SubRegion("Northern Europe", R.color.subcontinent),
            SubRegion("Western Europe", R.color.subcontinent),
            SubRegion("Southern Europe", R.color.subcontinent),
            SubRegion("Southeast Europe", R.color.subcontinent),
            SubRegion("Central Europe", R.color.subcontinent),
            SubRegion("Eastern Europe", R.color.subcontinent),

            SubRegion("Caribbean", R.color.subcontinent),
            SubRegion("Melanesia", R.color.subcontinent),
            SubRegion("Polynesia", R.color.subcontinent),
            SubRegion("Micronesia", R.color.subcontinent),
            SubRegion("Australia and New Zealand", R.color.subcontinent),

            SubRegion("Eastern Africa", R.color.subcontinent),
            SubRegion("Southern Africa", R.color.subcontinent),
            SubRegion("Middle Africa", R.color.subcontinent),
            SubRegion("Northern Africa", R.color.subcontinent),
            SubRegion("Western Africa", R.color.subcontinent),

            SubRegion("South America", R.color.subcontinent),
            SubRegion("North America", R.color.subcontinent),
            SubRegion("Central America", R.color.subcontinent),

            SubRegion("Western Asia", R.color.subcontinent),
            SubRegion("Central Asia", R.color.subcontinent),
            SubRegion("Southern Asia", R.color.subcontinent),
            SubRegion("South-Eastern Asia", R.color.subcontinent),
            SubRegion("Eastern Asia", R.color.subcontinent),
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun searchByContinent(continent: String) {
        val homeActivity = activity as HomeActivity
        homeActivity.changeToSearchByContinent(continent)
    }

    private fun searchBySubContinent(subContinent: String){
        val homeActivity = activity as HomeActivity
        homeActivity.changeToSearchBySubContinent(subContinent)
    }


}