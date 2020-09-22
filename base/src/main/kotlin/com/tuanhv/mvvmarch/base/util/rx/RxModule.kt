package com.tuanhv.mvvmarch.base.util.rx

import org.koin.dsl.module

/**
 * Created by hoang.van.tuan on 8/7/18.
 */
val rxModule = module {
    /**
     * Makes Koin provide [AppRxSchedulers] when a [RxSchedulers] type is requested
     */
    single<RxSchedulers> { AppRxSchedulers() }
}
