package com.countryapp.ui.view.fav.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.countryapp.databinding.FragmentFavBinding
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.view.home.HomeActivity
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
                binding.progressBar.isVisible = false
            }
        })

    }

    private fun initUI() {
        adapter = FavCountryAdapter (onItemSelected = { goToDetails(it) }, addFavCountry = {addFavCountry(it)}, removeFavCountry = {removeFavCountry(it)})
        binding.rvfav.setHasFixedSize(true)
        binding.rvfav.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvfav.adapter = adapter

        binding.progressBar.isVisible = true
        favViewModel.getAllCountries()

        binding.fabLogout.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog() {
        val act = activity as HomeActivity
        val builder: AlertDialog.Builder = AlertDialog.Builder(act)
        builder.setMessage("Are you sure you want to Log-Out?")
        builder.apply {
            setPositiveButton("Yes") { _, _ ->
                val act = activity as HomeActivity
                act.clearPreferences()
            }
            setNegativeButton("No", DialogInterface.OnClickListener{_, _ ->
            })
        }
        builder.create().show()

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