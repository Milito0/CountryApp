package com.countryapp.ui.view.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.countryapp.R
import com.countryapp.databinding.ActivityHomeBinding
import com.countryapp.ui.domain.model.CountryItem
import com.countryapp.ui.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(){

    companion object {
        lateinit var EMAIL: String
    }

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

        EMAIL = intent.extras!!.getString("email").toString()

        binding.navBar.setOnItemSelectedListener {
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

    fun clearPreferences(){
        val preferences = getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE).edit()
        preferences.clear()
        preferences.apply()
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}
