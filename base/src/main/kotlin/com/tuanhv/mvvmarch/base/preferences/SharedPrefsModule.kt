package com.tuanhv.mvvmarch.base.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefs
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefsDataSource
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@Module
class SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthSharedPreferences(authSharedPrefsDataSource: AuthSharedPrefsDataSource): AuthSharedPrefs {
        return authSharedPrefsDataSource
    }

}
