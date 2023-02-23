package com.luisguzman.pokemondm.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.luisguzman.pokemondm.R
import com.luisguzman.pokemondm.PokemonDmApp.Companion.sharedPreferences
import com.luisguzman.pokemondm.databinding.FragmentSignUpBinding
import com.luisguzman.pokemondm.databinding.FragmentSignUpBinding.bind
import com.luisguzman.pokemondm.ui.auth.viewModel.AuthViewModel
import com.luisguzman.pokemondm.ui.main.MainActivity
import com.luisguzman.pokemondm.utils.State
import com.luisguzman.pokemondm.utils.gone
import com.luisguzman.pokemondm.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = bind(view)
        setListeners()
    }

    private fun setListeners() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnRegister.setOnClickListener {
           validationField()
        }

    }

    private fun validationField() {
        if (binding.editName.text.isNullOrEmpty()) {
            binding.nameInput.error = "Field is empty"
        } else {
            binding.nameInput.error = null
        }

        if (binding.editLastName.text.isNullOrEmpty()) {
            binding.lastNameInput.error = "Field is empty"
        } else {
            binding.lastNameInput.error = null
        }

        if (binding.editEmail.text.isNullOrEmpty()) {
            binding.emailInput.error = "Field is empty"
        } else {
            binding.emailInput.error = null
        }

        if (binding.editPassword.text.isNullOrEmpty()) {
            binding.passwordInput.error = "Field is empty"
        } else {
            binding.passwordInput.error = null
        }

        if (binding.editConfirmPassword.text.isNullOrEmpty()) {
            binding.confirmPasswordInput.error = "Field is empty"
        } else {
            binding.confirmPasswordInput.error = null
        }

        if (binding.editPassword.text.toString() != binding.editConfirmPassword.text.toString()) {
            binding.passwordInput.error = "Passwords do not match"
            binding.confirmPasswordInput.error = "Passwords do not match"
        } else {
            binding.passwordInput.error = null
            binding.confirmPasswordInput.error = null
        }

        registerSignUp()
    }

    private fun registerSignUp() {
        val name = binding.editName.text.toString().trim()
        val lastName = binding.editLastName.text.toString().trim()
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        authViewModel.signUp(
            fullName = "$name $lastName", email = email, password = password
        ).observe(viewLifecycleOwner) { result ->
            when(result) {
                is State.Loading -> {
                    binding.loadingContainer.visible()
                    binding.containerRegisterView.gone()
                }
                is State.Success -> {
                    binding.loadingContainer.gone()
                    binding.containerRegisterView.gone()
                    navigateToHome()
                }
                is State.Failure -> {
                    binding.loadingContainer.gone()
                    binding.containerRegisterView.visible()
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
        sharedPreferences.setLoginValue(true)
    }

}