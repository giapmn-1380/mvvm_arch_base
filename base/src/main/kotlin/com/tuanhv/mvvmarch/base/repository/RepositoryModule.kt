package com.tuanhv.mvvmarch.base.repository

import com.tuanhv.mvvmarch.base.api.auth.AuthRemoteDataSource
import com.tuanhv.mvvmarch.base.api.post.PostsRemoteDataSource
import com.tuanhv.mvvmarch.base.api.user.UserRemoteDataSource
import com.tuanhv.mvvmarch.base.db.post.PostsLocalDataSource
import com.tuanhv.mvvmarch.base.db.user.UserLocalDataSource
import com.tuanhv.mvvmarch.base.preferences.auth.AuthSharedPrefsDataSource
import com.tuanhv.mvvmarch.base.repository.auth.AuthRepository
import com.tuanhv.mvvmarch.base.repository.auth.AuthRepositoryImpl
import com.tuanhv.mvvmarch.base.repository.post.PostRepository
import com.tuanhv.mvvmarch.base.repository.post.PostRepositoryImpl
import com.tuanhv.mvvmarch.base.repository.user.UserRepository
import com.tuanhv.mvvmarch.base.repository.user.UserRepositoryImpl
import org.koin.dsl.module

/**
 * Created by hoang.van.tuan on 9/20/20.
 */
val repositoryModule = module {
    /**
     * provide [AuthRepository]
     */
    single { AuthRemoteDataSource(get(), get()) }
    single { AuthSharedPrefsDataSource(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    /**
     * provide [UserRepository]
     */
    single { UserRemoteDataSource(get(), get()) }
    single { UserLocalDataSource(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    /**
     * provide [PostRepository]
     */
    single { PostsRemoteDataSource(get(), get()) }
    single { PostsLocalDataSource() }
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
}
