package com.tuanhv.mvvmarch.base.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@JsonClass(generateAdapter = true)
data class OauthToken(

        @Json(name = "access_token")
        val accessToken: String,

        @Json(name = "refresh_token")
        val refreshToken: String

)
