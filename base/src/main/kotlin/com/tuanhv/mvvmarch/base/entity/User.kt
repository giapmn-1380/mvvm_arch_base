package com.tuanhv.mvvmarch.base.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by hoang.van.tuan on 2/1/18.
 */
@JsonClass(generateAdapter = true)
@Entity(tableName = "user")
data class User(

        @PrimaryKey
        @Json(name = "access_token")
        val accessToken: String,

        @Json(name = "refresh_token")
        val refreshToken: String,

        @Json(name = "id")
        val id: String?,

        @Json(name = "email")
        val email: String?,

        @Json(name = "name")
        val name: String?

)
