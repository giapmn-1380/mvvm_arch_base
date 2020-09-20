package com.tuanhv.mvvmarch.base.preferences

import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefs
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class SharedPrefsModule {

    // Makes Dagger provide AuthSharedPrefsDataSource when a AuthSharedPrefs type is requested
    @Binds
    abstract fun provideAuthSharedPreferences(authSharedPrefsDataSource: AuthSharedPrefsDataSource): AuthSharedPrefs

}
