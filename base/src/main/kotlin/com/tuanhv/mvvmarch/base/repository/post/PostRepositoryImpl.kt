package com.tuanhv.mvvmarch.base.repository.post

import com.tuanhv.mvvmarch.base.api.post.PostsRemoteDataSource
import com.tuanhv.mvvmarch.base.db.post.PostsLocalDataSource
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.repository.common.Resource

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
class PostRepositoryImpl constructor(
        private val postsRemoteDataSource: PostsRemoteDataSource,
        private val postsLocalDataSource: PostsLocalDataSource
) : PostRepository {

    override suspend fun getPosts(afterId: Long): Resource<PaginatedEntities<Post>> {
        return postsRemoteDataSource.getPosts(afterId)
    }

}
