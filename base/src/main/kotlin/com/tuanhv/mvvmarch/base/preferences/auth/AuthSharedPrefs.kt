package com.tuanhv.mvvmarch.base.preferences.auth

/**
 * Created by hoang.van.tuan on 8/7/18.
 */

interface AuthSharedPrefs {

    /**
     * Get access token of user.
     */
    fun getAccessToken(): String

    /**
     * Save access token of user.
     */
    fun setAccessToken(accessToken: String)

    /**
     * Get refresh token of user.
     */
    fun getRefreshToken(): String

    /**
     * Save refresh token of user.
     */
    fun setRefreshToken(refreshToken: String)

}
