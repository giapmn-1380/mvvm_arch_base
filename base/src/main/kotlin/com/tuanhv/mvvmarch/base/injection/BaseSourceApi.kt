package com.tuanhv.mvvmarch.base.injection

import javax.inject.Qualifier

/**
 * Created by hoang.van.tuan on 9/14/20.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseSourceApi(val value: String)
