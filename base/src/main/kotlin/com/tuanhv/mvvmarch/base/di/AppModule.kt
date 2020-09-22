package com.tuanhv.mvvmarch.base.di

import com.tuanhv.mvvmarch.base.platform.AppManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by hoang.van.tuan on 9/22/20.
 */
val appModule = module {
    single { AppManager(androidContext()) }
}
