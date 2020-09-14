package com.tuanhv.mvvmarch.base.db.post

import com.tuanhv.mvvmarch.base.api.common.rxjava.Result
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.injection.PerUser
import com.tuanhv.mvvmarch.base.repository.post.PostsDataSource
import io.reactivex.Observable

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@PerUser
class PostsLocalDataSource : PostsDataSource {

    override fun getPosts(afterId: Long): Observable<Result<PaginatedEntities<Post>>> {
        TODO("Not yet implemented")
    }

}
