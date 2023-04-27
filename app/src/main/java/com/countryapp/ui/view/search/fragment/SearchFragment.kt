package com.countryapp.ui.view.search.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.countryapp.databinding.FragmentSearchBinding
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

            searchViewModel.countryData.observe(viewLifecycleOwner, Observer {
                adapter.updateList(it)
                Log.i("Milito", it.toString())
            })
        }
        //if(subContinent!=null)

    }

    private fun initUI() {
        adapter = CountryAdapter()
        binding.rvCountry.setHasFixedSize(true)
        binding.rvCountry.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvCountry.adapter = adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

}