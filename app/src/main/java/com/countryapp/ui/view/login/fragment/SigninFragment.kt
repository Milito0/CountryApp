package com.countryapp.ui.view.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.countryapp.databinding.FragmentSigninBinding
import com.countryapp.ui.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class SigninFragment : Fragment() {
    private var _binding: FragmentSigninBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
    }


    private fun initComponents() {
        binding.btnSignin.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && checkPassword()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
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

        binding.btnLogin.setOnClickListener {
            val activity = activity as LoginActivity
            activity.navigateToLogIn()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun checkPassword(): Boolean {
        if (binding.etPass.text.isEmpty()) return false

        if (binding.etPass.text.toString() != binding.etRepeatPass.text.toString()) {
            return false
        }

        return true

    }

}