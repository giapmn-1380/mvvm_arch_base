package com.tuanhv.mvvmarch.base.repository.auth

import com.tuanhv.mvvmarch.base.api.auth.AuthRemoteDataSource
import com.tuanhv.mvvmarch.base.api.common.SuccessState
import com.tuanhv.mvvmarch.base.api.common.rxjava.Result
import com.tuanhv.mvvmarch.base.entity.OauthToken
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefsDataSource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@Singleton
class AuthRepository @Inject constructor(
        private val authRemoteDataSource: AuthRemoteDataSource,
        private val authSharedPrefsDataSource: AuthSharedPrefsDataSource
) : AuthDataSource {

    override fun login(email: String, password: String): Observable<Result<OauthToken>> {
        return authRemoteDataSource.login(email, password)
                .doOnNext {
                    it.data?.let { authToken ->
                        authSharedPrefsDataSource.saveOauthToken(authToken)
                    }
                }
    }

    override fun getOauthToken(): OauthToken? {
        return authSharedPrefsDataSource.getOauthToken()
    }

    override fun logout(): Observable<Result<SuccessState>> {
        val accessToken = authSharedPrefsDataSource.getAccessToken()
        return authRemoteDataSource.logout(accessToken)
                .doOnNext {
                    authSharedPrefsDataSource.clearOauthToken()
                }
    }

}
