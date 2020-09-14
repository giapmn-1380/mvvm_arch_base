package com.tuanhv.mvvmarch.base.repository.auth

import com.tuanhv.mvvmarch.base.api.auth.AuthRemoteDataSource
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefsDataSource
import com.tuanhv.mvvmarch.base.repository.common.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
        private val authRemoteDataSource: AuthRemoteDataSource,
        private val authSharedPrefsDataSource: AuthSharedPrefsDataSource
) : AuthRepository {

    override suspend fun login(email: String, password: String): Resource<OauthToken> {
        return authRemoteDataSource.login(email, password)
    }

    override fun saveOauthToken(authToken: OauthToken) {
        authSharedPrefsDataSource.saveOauthToken(authToken)
    }

    override fun getOauthToken(): OauthToken? {
        return authSharedPrefsDataSource.getOauthToken()
    }

    override suspend fun logout(): Resource<SuccessState> {
        val accessToken = authSharedPrefsDataSource.getAccessToken()
        return authRemoteDataSource.logout(accessToken)
    }

    override fun clearOauthToken() {
        authSharedPrefsDataSource.clearOauthToken()
    }

}
