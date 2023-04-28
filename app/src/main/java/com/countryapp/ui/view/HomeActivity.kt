package com.countryapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.countryapp.R
import com.countryapp.databinding.ActivityHomeBinding
import com.countryapp.ui.domain.model.CountryItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(){

    lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.navBar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        binding.navBar.setOnItemSelectedListener {
            Log.i("Milito", "Hola")
            when (it.itemId) {
                binding.navBar.menu.getItem(0).itemId -> navController.navigate(it.itemId)
                binding.navBar.menu.getItem(1).itemId -> navController.navigate(it.itemId)
                binding.navBar.menu.getItem(2).itemId -> navController.navigate(it.itemId)
            }
            true

        }
    }

    fun changeToSearchByContinent(continent: String) {
        val myBundle = Bundle()
        myBundle.putString("CONTINENT", continent)
        navController.navigate(R.id.searchFragment, myBundle)
    }

    fun changeToSearchBySubContinent(subContinent: String) {
        val myBundle = Bundle()
        myBundle.putString("SUBCONTINENT", subContinent)
        navController.navigate(R.id.searchFragment, myBundle)
    }

    fun createDetailFragment(country: CountryItem){
        val myBundle = Bundle()
        myBundle.putString("ID", country.code)
        navController.navigate(R.id.detailFragment, myBundle)

    }

}
