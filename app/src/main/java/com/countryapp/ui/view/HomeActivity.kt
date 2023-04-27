package com.countryapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.countryapp.R
import com.countryapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

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

    fun changeToSearch(country: String) {
        val myBundle = Bundle()
        myBundle.putString("COUNTRY", country)
        navController.navigate(R.id.searchFragment, myBundle)
    }
}
