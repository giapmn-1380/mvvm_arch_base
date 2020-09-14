package com.tuanhv.mvvmarch.base.api.post

import com.tuanhv.mvvmarch.base.api.common.PaginatedResponse
import com.tuanhv.mvvmarch.base.entity.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hoang.van.tuan on 2/2/18.
 */

interface PostApi {

    @GET("/api/v1/get_posts.seam")
    fun getPosts(
            @Query("limit") limit: Int,
            @Query("after_id") afterId: Long
    ): Observable<PaginatedResponse<Post>>

}
