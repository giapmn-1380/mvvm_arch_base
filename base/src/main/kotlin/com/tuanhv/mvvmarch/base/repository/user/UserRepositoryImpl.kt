package com.tuanhv.mvvmarch.base.repository.user

import com.tuanhv.mvvmarch.base.api.user.UserRemoteDataSource
import com.tuanhv.mvvmarch.base.db.user.UserLocalDataSource

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
class UserRepositoryImpl constructor(
        private val userRemoteDataSource: UserRemoteDataSource,
        private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

}
