package com.tuanhv.mvvmarch.base.api.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@JsonClass(generateAdapter = true)
data class SuccessState(

        @Json(name = "message")
        val message: String?

)
