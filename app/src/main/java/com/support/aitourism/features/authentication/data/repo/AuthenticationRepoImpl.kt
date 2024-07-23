package com.support.aitourism.features.authentication.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.support.aitourism.features.authentication.data.datasource.remote_datasource.Resource
import com.support.aitourism.features.authentication.domain.repo.AuthenticationRepo

class AuthenticationRepoImpl(private val firebaseAuth: FirebaseAuth) : AuthenticationRepo {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser?> {
        lateinit var authResult: Resource<FirebaseUser?>
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result -> authResult = Resource.Success(result.user) }
            .addOnFailureListener { result -> authResult = Resource.Failure(result) }
        return authResult
    }

    override suspend fun signup() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() = firebaseAuth.signOut()
}