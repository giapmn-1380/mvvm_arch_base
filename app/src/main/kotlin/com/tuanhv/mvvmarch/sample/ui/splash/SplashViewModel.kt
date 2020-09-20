package com.tuanhv.mvvmarch.sample.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.repository.auth.AuthRepository

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
class SplashViewModel @ViewModelInject constructor(
        private val authRepository: AuthRepository
) : ViewModel() {

    fun getOauthToken(): OauthToken? {
        return authRepository.getOauthToken()
    }

}
