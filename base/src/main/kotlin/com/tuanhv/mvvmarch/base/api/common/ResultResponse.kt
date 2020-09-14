package com.tuanhv.mvvmarch.base.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 9/25/18.
 */
@JsonClass(generateAdapter = true)
data class ResultResponse<out T>(

        @Json(name = "result")
        val data: T?

)
