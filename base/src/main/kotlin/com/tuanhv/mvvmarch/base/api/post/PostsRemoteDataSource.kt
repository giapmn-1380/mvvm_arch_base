package com.tuanhv.mvvmarch.base.api.post

import com.tuanhv.mvvmarch.base.api.common.rxjava.Result
import com.tuanhv.mvvmarch.base.api.common.rxjava.retrofitPaginatedResponseToResult
import com.tuanhv.mvvmarch.base.entity.PaginatedEntities
import com.tuanhv.mvvmarch.base.entity.Post
import com.tuanhv.mvvmarch.base.util.rx.RxSchedulers
import io.reactivex.Observable

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
class PostsRemoteDataSource constructor(
        private val postApi: PostApi,
        private val rxSchedulers: RxSchedulers
) {

    companion object {
        private const val REQUEST_POSTS_LIMIT = 10
    }

    fun getPosts(afterId: Long): Observable<Result<PaginatedEntities<Post>>> {
        return postApi.getPosts(REQUEST_POSTS_LIMIT, afterId)
                .retrofitPaginatedResponseToResult()
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidThread())
    }

}
