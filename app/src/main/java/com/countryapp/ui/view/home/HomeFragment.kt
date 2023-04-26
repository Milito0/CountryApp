package com.countryapp.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.countryapp.databinding.FragmentHomeBinding
import com.countryapp.ui.view.home.recycleview.Region
import com.countryapp.ui.view.home.recycleview.RegionAdapter

class HomeFragment : Fragment() {

    private val regions = listOf(
        Region("Europa"),
        Region("America"),
        Region("Asia"),
        Region("Oceania"),
        Region("Africa")
    )
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var regionsAdapter: RegionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        regionsAdapter = RegionAdapter(regions)
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

}