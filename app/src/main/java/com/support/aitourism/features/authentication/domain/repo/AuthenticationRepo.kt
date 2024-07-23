package com.support.aitourism.features.authentication.domain.repo

import com.google.firebase.auth.FirebaseUser
import com.support.aitourism.features.authentication.data.datasource.remote_datasource.Resource


interface AuthenticationRepo {
   val currentUser: FirebaseUser?
   suspend fun login(email: String, password: String): Resource<FirebaseUser?>
   suspend fun signup()
   suspend fun logout()
}