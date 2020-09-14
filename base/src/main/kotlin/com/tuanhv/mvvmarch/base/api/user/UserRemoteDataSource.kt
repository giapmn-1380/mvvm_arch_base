package com.tuanhv.mvvmarch.base.api.user

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@Singleton
class UserRemoteDataSource @Inject constructor(
        private val userApi: UserApi
) {

}
