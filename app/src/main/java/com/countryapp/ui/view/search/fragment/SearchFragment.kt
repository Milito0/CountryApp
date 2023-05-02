package com.countryapp.ui.view.search.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.countryapp.databinding.FragmentSearchBinding
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.view.HomeActivity
import com.countryapp.ui.view.search.recyclerview_country.CountryAdapter
import com.countryapp.ui.view.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var continent: String? = null
    private var subContinent: String? = null

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var adapter: CountryAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            continent = it.getString("CONTINENT")
            subContinent = it.getString("SUBCONTINENT")
        }

        initUI()

        if(continent!=null) {
            searchViewModel.getCountries(continent.toString())
        }
        if(subContinent!=null){
            searchViewModel.getCountriesSubContinent(subContinent.toString())
        }

        searchViewModel.countryData.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

    }

    private fun initUI() {
        adapter = CountryAdapter{ goToDetails(it) }
        binding.rvCountry.setHasFixedSize(true)
        binding.rvCountry.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvCountry.adapter = adapter

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                when(binding.rgSearch.checkedRadioButtonId){
                    binding.rbContinent.id -> {
                        searchViewModel.getCountries(query.orEmpty())
                    }
                    binding.rbSubContinent.id -> {
                        searchViewModel.getCountriesSubContinent(query.orEmpty())
                    }
                }
                return false
            }
            // Solo para busquedas de paises por nombre, ya que la api solo permite aqui la busqueda parcial
            override fun onQueryTextChange(newText: String?): Boolean {
                if(binding.rbCountry.isChecked) searchViewModel.getCountriesByName(newText.orEmpty())
                return false
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun goToDetails(country: CountryItem){
        val homeActivity = activity as HomeActivity
        homeActivity.createDetailFragment(country)
    }

}