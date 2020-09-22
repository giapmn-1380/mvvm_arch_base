package com.tuanhv.mvvmarch.base.api.user

import com.tuanhv.mvvmarch.base.util.rx.RxSchedulers

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
class UserRemoteDataSource constructor(
        private val userApi: UserApi,
        private val rxSchedulers: RxSchedulers
) {

}
