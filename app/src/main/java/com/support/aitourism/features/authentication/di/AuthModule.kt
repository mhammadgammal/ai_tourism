package com.support.aitourism.features.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.support.aitourism.features.authentication.data.repo.AuthenticationRepoImpl
import com.support.aitourism.features.authentication.domain.repo.AuthenticationRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthenticationRepo(firebaseAuth: FirebaseAuth): AuthenticationRepo = AuthenticationRepoImpl(firebaseAuth)

}
