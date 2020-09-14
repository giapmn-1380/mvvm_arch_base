package com.tuanhv.mvvmarch.base.api.auth

import com.tuanhv.mvvmarch.base.api.auth.request.LoginRequest
import com.tuanhv.mvvmarch.base.api.auth.request.LogoutRequest
import com.tuanhv.mvvmarch.base.api.common.ResultResponse
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.entity.OauthToken
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
interface AuthApi {

    @POST("/api/v1/login.seam")
    fun login(
            @Body loginRequest: LoginRequest
    ): Observable<ResultResponse<OauthToken>>

    @POST("/api/v1/logout.seam")
    fun logout(
            @Body logoutRequest: LogoutRequest
    ): Observable<ResultResponse<SuccessState>>

}
