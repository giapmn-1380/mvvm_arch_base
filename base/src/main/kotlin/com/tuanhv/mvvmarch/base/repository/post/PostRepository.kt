package com.tuanhv.mvvmarch.base.repository.post

import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.repository.common.Resource

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
interface PostRepository {

    /**
     *  Fetch Posts from Network when paging.
     *
     *  @param afterId (required)
     **/
    suspend fun getPosts(afterId: Long): Resource<PaginatedEntities<Post>>

}
