package com.luisguzman.pokemondm.domain.auth

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signIn(email:String, password:String) : FirebaseUser?

    suspend fun signUp(fullName:String, email:String, password:String) : FirebaseUser?

}