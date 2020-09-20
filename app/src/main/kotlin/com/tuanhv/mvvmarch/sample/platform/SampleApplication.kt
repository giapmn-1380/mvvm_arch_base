package com.tuanhv.mvvmarch.sample.platform

import com.facebook.stetho.Stetho
import com.tuanhv.mvvmarch.base.platform.BaseApplication
import com.tuanhv.mvvmarch.base.util.DevUtils
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by hoang.van.tuan on 1/29/18.
 */
@HiltAndroidApp
class SampleApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initStetho()
    }

    private fun initStetho() {
        if (!DevUtils.isRunningTests()) {
            Stetho.initializeWithDefaults(this)
        }
    }

}
