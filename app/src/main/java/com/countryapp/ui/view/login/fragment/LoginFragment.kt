package com.countryapp.ui.view.login.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.countryapp.R
import com.countryapp.databinding.FragmentLoginBinding
import com.countryapp.ui.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()

        lastSession()
    }

    private fun lastSession() {
        val preferences = activity?.getSharedPreferences(
            getString(R.string.preferences_file),
            Context.MODE_PRIVATE
        )
        val email = preferences?.getString("email", null)

        if (email != null) {
            val activity = activity as LoginActivity
            activity.navigateToHome(email)
        }
    }

    private fun initComponents() {

        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPass.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val activity = activity as LoginActivity
                        activity.navigateToHome(binding.etEmail.text.toString())
                    }
                }
            }
        }


        binding.btnSignin.setOnClickListener {
            val activity = activity as LoginActivity
            activity.navigateToSignIn()
        }

        binding.btnGoogle.setOnClickListener {
            val activity = activity as LoginActivity
            activity.loginGoogle()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

}