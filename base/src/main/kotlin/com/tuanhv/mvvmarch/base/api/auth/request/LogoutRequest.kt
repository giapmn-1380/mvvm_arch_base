package com.tuanhv.mvvmarch.base.api.auth.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 9/19/20.
 */
@JsonClass(generateAdapter = true)
data class LogoutRequest(

        @Json(name = "fcm_token")
        val fcmToken: String

)
