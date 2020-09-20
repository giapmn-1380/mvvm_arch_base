package com.tuanhv.mvvmarch.base.repository.post

import com.tuanhv.mvvmarch.base.api.common.rxjava.Result
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.Post
import io.reactivex.Observable

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
interface PostRepository {

    /**
     *  Fetch Posts from Network when paging.
     *
     *  @param afterId (required)
     **/
    fun getPosts(afterId: Long): Observable<Result<PaginatedEntities<Post>>>

}
