package com.tuanhv.mvvmarch.base.repository.auth

import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.repository.common.Resource

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
interface AuthRepository {

    /**
     *  Call login to network
     *
     *  @param email (required)
     *  @param password (required)
     **/
    suspend fun login(
            email: String,
            password: String
    ): Resource<OauthToken>

    /**
     *  Save Oauth Token to Shared Preferences.
     *
     **/
    fun saveOauthToken(authToken: OauthToken)

    /**
     *  Get Oauth Token from Shared Preferences.
     *
     **/
    fun getOauthToken(): OauthToken?

    /**
     *  Call logout to network
     *
     **/
    suspend fun logout(): Resource<SuccessState>

    /**
     *  Clear Oauth Token from Shared Preferences.
     *
     **/
    fun clearOauthToken()

}
