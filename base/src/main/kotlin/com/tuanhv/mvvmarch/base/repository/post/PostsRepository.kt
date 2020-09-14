package com.tuanhv.mvvmarch.base.repository.post

import com.tuanhv.mvvmarch.base.api.common.rxjava.Result
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.injection.PerUser
import com.tuanhv.mvvmarch.base.injection.SourceLocal
import com.tuanhv.mvvmarch.base.injection.SourceRemote
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@PerUser
class PostsRepository @Inject constructor(
        @SourceRemote private val postsRemoteDataSource: PostsDataSource,
        @SourceLocal private val postsLocalDataSource: PostsDataSource
) : PostsDataSource {

    override fun getPosts(afterId: Long): Observable<Result<PaginatedEntities<Post>>> {
        return postsRemoteDataSource.getPosts(afterId)
    }

}
