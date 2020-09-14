package com.tuanhv.mvvmarch.base.repository.post

import com.tuanhv.mvvmarch.base.api.post.PostApi
import dagger.Module
import dagger.Provides
import com.tuanhv.mvvmarch.base.api.post.PostsRemoteDataSource
import com.tuanhv.mvvmarch.base.db.post.PostsLocalDataSource
import com.tuanhv.mvvmarch.base.injection.PerUser
import com.tuanhv.mvvmarch.base.injection.SourceLocal
import com.tuanhv.mvvmarch.base.injection.SourceRemote
import com.tuanhv.mvvmarch.base.util.rx.RxSchedulers

/**
 * Created by hoang.van.tuan on 2/5/18.
 */
@Module
class PostsRepositoryModule {

    @PerUser
    @Provides
    @SourceLocal
    fun providePostsLocalDataSource(): PostsDataSource {
        return PostsLocalDataSource()
    }

    @PerUser
    @Provides
    @SourceRemote
    fun providePostsRemoteDataSource(postApi: PostApi, rxSchedulers: RxSchedulers): PostsDataSource {
        return PostsRemoteDataSource(postApi, rxSchedulers)
    }

}
