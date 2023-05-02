package com.countryapp.ui.view.fav.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.countryapp.databinding.FragmentFavBinding
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.view.HomeActivity
import com.countryapp.ui.view.fav.recyclerview_favcountry.FavCountryAdapter
import com.countryapp.ui.view.fav.viewmodel.FavViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment() {

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavCountryAdapter
    private val favViewModel: FavViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        favViewModel.countryData.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                adapter.updateList(it)
            }
        })

    }

    private fun initUI() {
        Log.i("Milito", "1234")
        adapter = FavCountryAdapter (onItemSelected = { goToDetails(it) }, addFavCountry = {addFavCountry(it)}, removeFavCountry = {removeFavCountry(it)})
        binding.rvfav.setHasFixedSize(true)
        binding.rvfav.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvfav.adapter = adapter

        favViewModel.getAllCountries()
    }

    private fun goToDetails(country: CountryItem) {
        val homeActivity = activity as HomeActivity
        homeActivity.createDetailFragment(country)
    }
    private fun addFavCountry(country: CountryItem){
        favViewModel.insertFavCountry(country)
    }
    private fun removeFavCountry(country: CountryItem){
        favViewModel.removeFavCountry(country)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

}