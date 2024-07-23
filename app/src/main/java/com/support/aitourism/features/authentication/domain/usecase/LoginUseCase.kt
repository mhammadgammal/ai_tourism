package com.support.aitourism.features.authentication.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.support.aitourism.core.base_usecase.ParameterUseCase
import com.support.aitourism.features.authentication.data.datasource.remote_datasource.Resource
import com.support.aitourism.features.authentication.data.repo.AuthenticationRepoImpl
import com.support.aitourism.features.authentication.domain.entity.UserModel

class LoginUseCase(private val authenticationRepo: AuthenticationRepoImpl) : ParameterUseCase<String, Resource<FirebaseUser?>>() {
    override suspend fun execute(vararg parameters: String): Resource<FirebaseUser?> {
        val result = authenticationRepo.login(parameters[0], parameters[1])
        return result
    }
}
