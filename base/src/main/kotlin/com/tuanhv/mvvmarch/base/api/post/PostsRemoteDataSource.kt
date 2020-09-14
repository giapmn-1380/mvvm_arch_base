package com.tuanhv.mvvmarch.base.api.post

import com.tuanhv.mvvmarch.base.api.common.coroutines.BaseRemoteDataSource
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.repository.common.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@Singleton
class PostsRemoteDataSource @Inject constructor(
        private val postApi: PostApi
) : BaseRemoteDataSource() {

    companion object {
        private const val REQUEST_POSTS_LIMIT = 10
    }

    suspend fun getPosts(afterId: Long): Resource<PaginatedEntities<Post>> {
        return getResultFromPaginatedResponse { postApi.getPosts(REQUEST_POSTS_LIMIT, afterId) }
    }

}
