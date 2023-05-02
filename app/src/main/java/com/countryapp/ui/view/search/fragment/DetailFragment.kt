package com.countryapp.ui.view.search.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.countryapp.R
import com.countryapp.databinding.FragmentDetailBinding
import com.countryapp.ui.domain.model.DetailCountryItem
import com.countryapp.ui.view.search.viewmodel.DetailViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap
    private var id: String? = null
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var myCountry: DetailCountryItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = it.getString("ID")
        }



        if (id != null) {

            detailViewModel.getCountry(id.toString())

            detailViewModel.countryInfo.observe(viewLifecycleOwner, Observer {
                myCountry = it
                initListeners()
                updateView(myCountry)
            })
        }
    }

    private fun initListeners() {
        binding.ivHeart.setOnClickListener{
            if(myCountry.fav){
                binding.ivHeart.setImageResource(R.drawable.ic_favborder_details)
                detailViewModel.removeFavCountry(myCountry)
            }else{
                binding.ivHeart.setImageResource(R.drawable.ic_fav_detail)
                detailViewModel.insertFavCountry(myCountry)
            }
            myCountry.fav = !myCountry.fav
        }
    }

    private fun updateView(country: DetailCountryItem) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (country.flags != null)
            Picasso.get().load(country.flags.png).into(binding.ivDetailCountry)
        if(country.fav) binding.ivHeart.setImageResource(R.drawable.ic_fav_detail)
        binding.tvDetailTittle.text = country.name.common
        binding.tvDetailCapital.text = country.capital!![0]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        createMarker()
    }

    private fun createMarker() {
        val coordinates = LatLng(myCountry.latlng[0].toDouble(), myCountry.latlng[1].toDouble())
        val marker = MarkerOptions().position(coordinates).title(myCountry.name.common)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 5f),
            3000,
            null
        )
    }


}