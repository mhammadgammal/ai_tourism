package com.support.aitourism.features.authentication.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.support.aitourism.features.authentication.data.datasource.remote_datasource.Resource
import com.support.aitourism.features.authentication.domain.entity.UserModel
import com.support.aitourism.features.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val loginUseCase: LoginUseCase) :
    ViewModel() {

    private lateinit var user: UserModel
    private var _result = MutableStateFlow<Resource<FirebaseUser?>?>(null)
    val result = _result.asStateFlow()


    fun login(email: String, password: String) = viewModelScope.launch {
        _result.value = Resource.Loading
        _result.value = loginUseCase.execute(email, password)
        _result.value.let { result ->
            result.let {
                if (result is Resource.Success) {
                    user = UserModel(
                        id = result.result?.uid,
                        email = result.result?.email!!,
                        name = result.result.displayName, password = ""
                    )
                    println(user.email)
                }
            }

        }


    }
}