package com.tuanhv.mvvmarch.base.api.common.coroutines

import com.tuanhv.mvvmarch.base.api.common.ErrorState
import com.tuanhv.mvvmarch.base.api.common.ErrorStatus.*
import com.tuanhv.mvvmarch.base.api.common.PaginatedResponse
import com.tuanhv.mvvmarch.base.api.common.ResultResponse
import com.tuanhv.mvvmarch.base.api.common.coroutines.NetworkResponse.*
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.repository.common.Resource
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by hoang.van.tuan on 9/17/20.
 */
abstract class BaseRemoteDataSource {

    protected suspend fun <T : Any> getResultFromResponse(
            call: suspend () -> NetworkResponse<T, ErrorState>
    ): Resource<T> {
        return try {
            when (val response = call()) {
                is Success -> asResult(response.body)
                is ApiError -> asErrorResult(response.body)
                is NetworkError -> onHandleNetworkError(response.error)
                is UnknownError -> asErrorResult(ErrorState(UNKNOWN_ERROR.message, UNKNOWN_ERROR.code))
            }
        } catch (e: Exception) {
            asErrorResult(ErrorState(COMMON.message, COMMON.code))
        }
    }

    private fun <T> asResult(t: T): Resource<T> {
        return Resource.success(t)
    }

    protected suspend fun <T> getResultFromResultResponse(
            call: suspend () -> NetworkResponse<ResultResponse<T>, ErrorState>
    ): Resource<T> {
        return try {
            when (val response = call()) {
                is Success -> asResult(response.body)
                is ApiError -> asErrorResult(response.body)
                is NetworkError -> onHandleNetworkError(response.error)
                is UnknownError -> asErrorResult(ErrorState(UNKNOWN_ERROR.message, UNKNOWN_ERROR.code))
            }
        } catch (e: Exception) {
            asErrorResult(ErrorState(COMMON.message, COMMON.code))
        }
    }

    private fun <T> asResult(resultResponse: ResultResponse<T>): Resource<T> {
        return Resource.success(resultResponse.data)
    }

    protected suspend fun <T> getResultFromPaginatedResponse(
            call: suspend () -> NetworkResponse<PaginatedResponse<T>, ErrorState>
    ): Resource<PaginatedEntities<T>> {
        return try {
            when (val response = call()) {
                is Success -> asPaginatedEntitiesResult(response.body)
                is ApiError -> asErrorResult(response.body)
                is NetworkError -> onHandleNetworkError(response.error)
                is UnknownError -> asErrorResult(ErrorState(UNKNOWN_ERROR.message, UNKNOWN_ERROR.code))
            }
        } catch (e: Exception) {
            asErrorResult(ErrorState(COMMON.message, COMMON.code))
        }
    }

    private fun <T> asPaginatedEntitiesResult(paginatedResponse: PaginatedResponse<T>): Resource<PaginatedEntities<T>> {
        val paginatedEntities = PaginatedEntities(
                paginatedResponse.data ?: emptyList(),
                paginatedResponse.pagination?.afterId
        )
        return Resource.success(paginatedEntities)
    }

    private fun <T> onHandleNetworkError(error: IOException): Resource<T>  {
        error.printStackTrace()
        return when (error) {
            is UnknownHostException -> asErrorResult(ErrorState(UNKNOWN_HOST.message, UNKNOWN_HOST.code))
            is SocketTimeoutException -> asErrorResult(ErrorState(TIME_OUT.message, TIME_OUT.code))
            else -> asErrorResult(ErrorState(NETWORK_ERROR.message, NETWORK_ERROR.code))
        }
    }

    private fun <T> asErrorResult(errorState: ErrorState): Resource<T> {
        return Resource.error(errorState)
    }

}
