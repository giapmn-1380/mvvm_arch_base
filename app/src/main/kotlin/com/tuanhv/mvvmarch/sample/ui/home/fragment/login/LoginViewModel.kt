package com.tuanhv.mvvmarch.sample.ui.home.fragment.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuanhv.mvvmarch.base.api.common.ErrorState
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.repository.auth.AuthRepository
import com.tuanhv.mvvmarch.base.repository.common.Resource.Status.ERROR
import com.tuanhv.mvvmarch.base.repository.common.Resource.Status.SUCCESS
import com.tuanhv.mvvmarch.base.ui.SingleLiveData
import kotlinx.coroutines.launch

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
class LoginViewModel constructor(
        private val authRepository: AuthRepository
) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    private val successLogin = SingleLiveData<OauthToken>()
    private val errorLogin = SingleLiveData<ErrorState>()

    val isLoading = ObservableField(false)

    fun getSuccessLogin(): SingleLiveData<OauthToken> {
        return successLogin
    }

    fun getErrorLogin(): SingleLiveData<ErrorState> {
        return errorLogin
    }

    fun login(
            email: String,
            password: String
    ) {
        isLoading.set(true)
        viewModelScope.launch {
            val response = authRepository.login(email, password)
            isLoading.set(false)
            when (response.status) {
                SUCCESS -> {
                    Log.d(TAG, "login: onSuccess - ${response.data}")
                    response.data?.let {
                        authRepository.saveOauthToken(it)
                    }
                    successLogin.postValue(response.data)
                }
                ERROR -> {
                    Log.d(TAG, "login: onError - ${response.errorState}")
                    errorLogin.postValue(response.errorState)
                }
            }
        }
    }

}
