package com.tuanhv.mvvmarch.base.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@JsonClass(generateAdapter = true)
data class Post(

        @Json(name = "id")
        val id: Long,

        @Json(name = "title")
        val title: String,

        @Json(name = "image")
        val image: String,

        @Json(name = "publish_date")
        val publishAt: String

)
