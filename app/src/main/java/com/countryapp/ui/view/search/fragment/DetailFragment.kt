package com.countryapp.ui.view.search.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.countryapp.databinding.FragmentDetailBinding
import com.countryapp.ui.domain.model.DetailCountryItem
import com.countryapp.ui.view.search.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var id: String? = null
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = it.getString("ID")
        }

        Log.i("Milito", id.toString())
        if(id!=null){

            detailViewModel.getCountry(id.toString())

            detailViewModel.countryInfo.observe(viewLifecycleOwner, Observer {
                updateView(it)
            })
        }
    }

    private fun updateView(country: DetailCountryItem) {
        if(country.flags!= null)
            Picasso.get().load(country.flags.png).into(binding.ivDetailCountry)
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


}