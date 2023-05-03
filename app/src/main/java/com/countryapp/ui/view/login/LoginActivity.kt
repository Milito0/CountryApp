package com.countryapp.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.countryapp.R
import com.countryapp.databinding.ActivityLoginBinding
import com.countryapp.ui.view.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }


    fun navigateToSignIn(){
        navController.navigate(R.id.signinFragment)
    }
    fun navigateToHome(email: String){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}