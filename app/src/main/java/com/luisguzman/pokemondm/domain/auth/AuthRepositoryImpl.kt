package com.luisguzman.pokemondm.domain.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.luisguzman.pokemondm.data.network.source.AuthDataSource
import com.luisguzman.pokemondm.utils.State
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository  {

    override suspend fun signIn(email: String, password: String): FirebaseUser? {
       return dataSource.signIn(email, password)
    }

    override suspend fun signUp(fullName:String, email: String, password: String): FirebaseUser? {
        return dataSource.signUp(fullName, email, password)
    }

}