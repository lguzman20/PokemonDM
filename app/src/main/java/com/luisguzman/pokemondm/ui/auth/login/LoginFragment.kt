package com.luisguzman.pokemondm.ui.auth.login

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.luisguzman.pokemondm.PokemonDmApp.Companion.sharedPreferences
import com.luisguzman.pokemondm.R
import com.luisguzman.pokemondm.databinding.FragmentLoginBinding
import com.luisguzman.pokemondm.databinding.FragmentLoginBinding.bind
import com.luisguzman.pokemondm.ui.auth.viewModel.AuthViewModel
import com.luisguzman.pokemondm.ui.main.MainActivity
import com.luisguzman.pokemondm.utils.State
import com.luisguzman.pokemondm.utils.errorToastMessage
import com.luisguzman.pokemondm.utils.gone
import com.luisguzman.pokemondm.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = bind(view)

        // Firebase Client
        auth = FirebaseAuth.getInstance()

        // Google Client
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        setListeners()
    }

    private fun setListeners() {

        binding.btnLogin.setOnClickListener {
            validationField()
        }

        binding.containerSignUp.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.googleContainer.btnGoogle.setOnClickListener {
           signInGoogle()
        }
    }

    private fun validationField() {
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

        loginObserve()
    }

    private fun loginObserve() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        authViewModel.signIn(email, password).observe(viewLifecycleOwner) { result ->
            when(result){
                is State.Loading -> {
                    binding.loadingContainer.visible()
                    binding.containerView.gone()
                }
                is State.Success -> {
                    binding.loadingContainer.gone()
                    binding.containerView.gone()
                    navigateToHome()
                }
                is State.Failure -> {
                    binding.loadingContainer.gone()
                    binding.containerView.visible()
                    dialogNotRegister()
                    Log.d("FirebaseUser", "Error User")
                }
            }
        }
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            errorToastMessage(requireContext(), task.exception.toString())
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                authViewModel.saveUserInFirebase(
                    fullName = account.displayName.toString(),
                    email = account.email.toString()
                )
                navigateToHome()
            }else{
                errorToastMessage(requireContext(), it.exception.toString())
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
        sharedPreferences.setLoginValue(true)
    }

    @SuppressLint("InflateParams")
    private fun dialogNotRegister() {
        val dialogBinding = layoutInflater.inflate(R.layout.dialog_user_not_register, null)
        val dialog = Dialog(requireContext())
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding)
        dialog.window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

       val signUpButton = dialogBinding.findViewById<MaterialButton>(R.id.register)
        signUpButton.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}