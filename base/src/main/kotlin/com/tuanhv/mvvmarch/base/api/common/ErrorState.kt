package com.tuanhv.mvvmarch.base.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@JsonClass(generateAdapter = true)
data class ErrorState(
        @Json(name = "error_message")
        val errorMessage: String?,

        @Json(name = "error_code")
        val errorCode: Int?
)
