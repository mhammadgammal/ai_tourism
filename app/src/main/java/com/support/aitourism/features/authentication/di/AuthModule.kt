package com.support.aitourism.features.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.support.aitourism.features.authentication.data.repo.AuthenticationRepoImpl
import com.support.aitourism.features.authentication.domain.repo.AuthenticationRepo
import com.support.aitourism.features.authentication.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthenticationRepo(firebaseAuth: FirebaseAuth): AuthenticationRepo = AuthenticationRepoImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideLoginUseCase(authenticationRepo: AuthenticationRepo): LoginUseCase = LoginUseCase(authenticationRepo)

}
