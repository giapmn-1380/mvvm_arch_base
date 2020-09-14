package com.tuanhv.mvvmarch.base.repository.common

import com.tuanhv.mvvmarch.base.api.common.ErrorState

/**
 * Created by hoang.van.tuan on 9/17/20.
 */
data class Resource<out T>(val status: Status, val data: T?, val errorState: ErrorState?) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(errorState: ErrorState, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, errorState)
        }
    }
}
