package com.tuanhv.mvvmarch.sample.platform

import com.facebook.stetho.Stetho
import com.tuanhv.mvvmarch.base.api.networkModule
import com.tuanhv.mvvmarch.base.db.databaseModule
import com.tuanhv.mvvmarch.base.di.appModule
import com.tuanhv.mvvmarch.base.platform.BaseApplication
import com.tuanhv.mvvmarch.base.preferences.sharedPrefsModule
import com.tuanhv.mvvmarch.base.repository.repositoryModule
import com.tuanhv.mvvmarch.base.util.DevUtils
import com.tuanhv.mvvmarch.base.util.rx.rxModule
import com.tuanhv.mvvmarch.sample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by hoang.van.tuan on 1/29/18.
 */
class SampleApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initStetho()

        // Initialize Koin
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@SampleApplication)

            // module list
            modules(
                    appModule,
                    rxModule,
                    sharedPrefsModule,
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
            )
        }
    }

    private fun initStetho() {
        if (!DevUtils.isRunningTests()) {
            Stetho.initializeWithDefaults(this)
        }
    }

}
