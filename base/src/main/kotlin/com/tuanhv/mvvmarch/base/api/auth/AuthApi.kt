package com.tuanhv.mvvmarch.base.api.auth

import com.tuanhv.mvvmarch.base.api.auth.request.LoginRequest
import com.tuanhv.mvvmarch.base.api.auth.request.LogoutRequest
import com.tuanhv.mvvmarch.base.api.common.ErrorState
import com.tuanhv.mvvmarch.base.api.common.ResultResponse
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.api.common.coroutines.NetworkResponse
import com.tuanhv.mvvmarch.base.entity.OauthToken
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
interface AuthApi {

    @POST("/api/v1/login.seam")
    suspend fun login(
            @Body loginRequest: LoginRequest
    ): NetworkResponse<ResultResponse<OauthToken>, ErrorState>

    @POST("/api/v1/logout.seam")
    suspend fun logout(
            @Body logoutRequest: LogoutRequest
    ): NetworkResponse<ResultResponse<SuccessState>, ErrorState>

}
