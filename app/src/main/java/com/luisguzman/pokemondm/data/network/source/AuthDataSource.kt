package com.luisguzman.pokemondm.data.network.source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.luisguzman.pokemondm.data.network.source.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSource @Inject constructor() {

    suspend fun signIn(email:String, password:String) : FirebaseUser? {
        val result = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return result.user
    }

    suspend fun signUp(fullName:String, email:String, password:String) : FirebaseUser? {
        val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        result.user?.uid.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid.toString())
                .set(User(fullName, email))
        }
        return result.user
    }

}