package com.tuanhv.mvvmarch.sample.ui.splash

import androidx.lifecycle.ViewModel
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.injection.PerActivity
import com.tuanhv.mvvmarch.base.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@PerActivity
class SplashViewModel @Inject constructor(
        private val authRepository: AuthRepository
) : ViewModel() {

    fun getOauthToken(): OauthToken? {
        return authRepository.getOauthToken()
    }

}
