package com.tuanhv.mvvmarch.base.repository.auth

import com.tuanhv.mvvmarch.base.api.auth.AuthApi
import com.tuanhv.mvvmarch.base.api.auth.AuthRemoteDataSource
import com.tuanhv.mvvmarch.base.preferences.SharedPrefsApi
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefsDataSource
import com.tuanhv.mvvmarch.base.util.rx.RxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@Module
class AuthRepositoryModule {

    @Singleton
    @Provides
    fun provideAuthSharedPrefsDataSource(sharedPrefsApi: SharedPrefsApi): AuthSharedPrefsDataSource {
        return AuthSharedPrefsDataSource(sharedPrefsApi)
    }

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authApi: AuthApi, rxSchedulers: RxSchedulers): AuthRemoteDataSource {
        return AuthRemoteDataSource(authApi, rxSchedulers)
    }

}
