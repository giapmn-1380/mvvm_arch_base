package com.tuanhv.mvvmarch.base.api.auth.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@JsonClass(generateAdapter = true)
data class LoginRequest(

        @Json(name = "email")
        val email: String,

        @Json(name = "password")
        val password: String

)
