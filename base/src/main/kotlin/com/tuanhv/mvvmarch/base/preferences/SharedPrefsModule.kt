package com.tuanhv.mvvmarch.base.preferences

import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefs
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefsDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
val sharedPrefsModule = module {
    /**
     * provide [SharedPrefsApi]
     */
    single { SharedPrefsApi(androidContext()) }
    /**
     * Makes Koin provide [AuthSharedPrefsDataSource] when a [AuthSharedPrefs] type is requested
     */
    single<AuthSharedPrefs> { get<AuthSharedPrefsDataSource>() }
}
