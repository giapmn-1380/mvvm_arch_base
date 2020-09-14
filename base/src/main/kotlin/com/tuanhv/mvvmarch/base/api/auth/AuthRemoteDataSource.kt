package com.tuanhv.mvvmarch.base.api.auth

import com.tuanhv.mvvmarch.base.api.auth.request.LoginRequest
import com.tuanhv.mvvmarch.base.api.auth.request.LogoutRequest
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.api.common.rxjava.Result
import com.tuanhv.mvvmarch.base.api.common.rxjava.retrofitResultResponseToResult
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.util.rx.RxSchedulers
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@Singleton
class AuthRemoteDataSource @Inject constructor(
        private val authApi: AuthApi,
        private val rxSchedulers: RxSchedulers
) {

    fun login(email: String, password: String): Observable<Result<OauthToken>> {
        return authApi.login(LoginRequest(email,password))
                .retrofitResultResponseToResult()
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidThread())
    }

    fun logout(fcmToken: String): Observable<Result<SuccessState>> {
        return authApi.logout(LogoutRequest(fcmToken))
                .retrofitResultResponseToResult()
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidThread())
    }
}
