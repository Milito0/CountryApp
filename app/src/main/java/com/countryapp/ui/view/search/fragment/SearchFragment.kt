package com.countryapp.ui.view.search.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
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

        if (continent != null) {
            binding.progressBar.isVisible = true
            binding.svSearch.setQuery(continent, false)
            binding.rbContinent.isChecked = true
            searchViewModel.getCountries(continent.toString())
        }
        if (subContinent != null) {
            binding.rbSubContinent.isChecked = true
            binding.svSearch.setQuery(subContinent, false)
            binding.progressBar.isVisible = true
            searchViewModel.getCountriesSubContinent(subContinent.toString())
        }

        searchViewModel.countryData.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                adapter.updateList(it)
            }
            binding.progressBar.isVisible = false
        })

    }

    private fun initUI() {
        adapter = CountryAdapter { goToDetails(it) }
        binding.rvCountry.setHasFixedSize(true)
        binding.rvCountry.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvCountry.adapter = adapter

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                when (binding.rgSearch.checkedRadioButtonId) {
                    binding.rbContinent.id -> {
                        binding.progressBar.isVisible = true
                        searchViewModel.getCountries(query.orEmpty())
                    }

                    binding.rbSubContinent.id -> {
                        binding.progressBar.isVisible = true
                        searchViewModel.getCountriesSubContinent(query.orEmpty())
                    }

                    binding.rbCountry.id -> {
                        binding.progressBar.isVisible = true
                        searchViewModel.getCountriesByName(query.orEmpty())
                    }
                }
                return false
            }

            // Solo para busquedas de paises por nombre, ya que la api solo permite aqui la busqueda parcial
            override fun onQueryTextChange(newText: String?): Boolean {
                if (binding.rbCountry.isChecked){
                    binding.progressBar.isVisible = true
                    searchViewModel.getCountriesByName(newText.orEmpty())
                }
                return false
            }
        })

        // A la hora de cambiar el radio button, hago que el search view busque de nuevo
        binding.rgSearch.setOnCheckedChangeListener { _, _ ->
            binding.svSearch.setQuery(
                binding.svSearch.query,
                true
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun goToDetails(country: CountryItem) {
        val homeActivity = activity as HomeActivity
        homeActivity.createDetailFragment(country)
    }

}