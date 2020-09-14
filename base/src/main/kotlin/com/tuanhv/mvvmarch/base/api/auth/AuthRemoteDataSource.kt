package com.tuanhv.mvvmarch.base.api.auth

import com.tuanhv.mvvmarch.base.api.auth.request.LoginRequest
import com.tuanhv.mvvmarch.base.api.auth.request.LogoutRequest
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.api.common.coroutines.BaseRemoteDataSource
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.repository.common.Resource

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
class AuthRemoteDataSource constructor(
        private val authApi: AuthApi
) : BaseRemoteDataSource() {

    suspend fun login(email: String, password: String): Resource<OauthToken> {
        return getResultFromResultResponse { authApi.login(LoginRequest(email, password)) }
    }

    suspend fun logout(fcmToken: String): Resource<SuccessState> {
        return getResultFromResultResponse { authApi.logout(LogoutRequest(fcmToken)) }
    }

}
