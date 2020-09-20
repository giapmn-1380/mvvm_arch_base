package com.tuanhv.mvvmarch.base.util.rx

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by hoang.van.tuan on 8/7/18.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class RxModule {

    // Makes Dagger provide AppRxSchedulers when a RxSchedulers type is requested
    @Binds
    abstract fun provideRxSchedulers(appRxSchedulers: AppRxSchedulers): RxSchedulers

}
