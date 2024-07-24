package com.support.aitourism.features.authentication.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.support.aitourism.features.authentication.data.datasource.remote_datasource.Resource
import com.support.aitourism.features.authentication.domain.repo.AuthenticationRepo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepoImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthenticationRepo {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser?> = try {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
        Resource.Success(firebaseAuth.currentUser)
    } catch (e: Exception) {
        println(e.message)
        Resource.Failure(e)
    }


    override suspend fun signup() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() = firebaseAuth.signOut()
}