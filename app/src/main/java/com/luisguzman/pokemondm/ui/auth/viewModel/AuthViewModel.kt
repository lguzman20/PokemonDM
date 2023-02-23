package com.luisguzman.pokemondm.ui.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.luisguzman.pokemondm.data.network.source.model.User
import com.luisguzman.pokemondm.domain.auth.AuthRepository
import com.luisguzman.pokemondm.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun saveUserInFirebase(fullName:String, email:String) {
        FirebaseAuth.getInstance().currentUser?.uid.let { uid ->
            FirebaseFirestore.getInstance().collection("login").document(uid.toString()).set(
                User(fullName = fullName, email = email))
        }
    }

    fun signIn(email:String, password:String) = liveData(Dispatchers.IO) {
        emit(State.Loading())
        try {
           emit(State.Success(repository.signIn(email, password)))
        } catch (e:Exception) {
            emit(State.Failure(e))
        }
    }

    fun signUp(fullName: String, email:String, password:String) = liveData(Dispatchers.IO) {
        emit(State.Loading())
        try {
            emit(State.Success(repository.signUp(fullName, email, password)))
        } catch (e:Exception) {
            emit(State.Failure(e))
        }
    }


}