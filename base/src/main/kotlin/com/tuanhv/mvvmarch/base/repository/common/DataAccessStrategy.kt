package com.tuanhv.mvvmarch.base.repository.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.tuanhv.mvvmarch.base.repository.common.Resource.Status.*
import kotlinx.coroutines.Dispatchers

/**
 * Created by hoang.van.tuan on 9/17/20.
 */
fun <T> performGetOperation(networkCall: suspend () -> Resource<T>): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            val response = networkCall.invoke()
            emit(response)
        }

fun <T> performFetchAndSaveOperation(networkCall: suspend () -> Resource<T>,
                                     saveCallResult: suspend (T) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            val result = networkCall.invoke()
            if (result.status == SUCCESS) {
                result.data?.let {
                    saveCallResult(it)
                }
            }
            emit(result)
        }
