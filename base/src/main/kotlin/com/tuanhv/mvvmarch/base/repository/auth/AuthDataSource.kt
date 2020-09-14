package com.tuanhv.mvvmarch.base.repository.auth

import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.api.common.rxjava.Result
import com.tuanhv.mvvmarch.base.entity.OauthToken
import io.reactivex.Observable

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
interface AuthDataSource {

    /**
     *  Call login to network
     *
     *  @param email (required)
     *  @param password (required)
     **/
    fun login(
            email: String,
            password: String
    ): Observable<Result<OauthToken>>

    /**
     *  Get Oauth Token from Shared Preferences.
     *
     **/
    fun getOauthToken(): OauthToken?

    /**
     *  Call logout to network
     *
     **/
    fun logout(): Observable<Result<SuccessState>>

}
