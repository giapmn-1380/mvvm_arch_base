package com.tuanhv.mvvmarch.base.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.tuanhv.mvvmarch.base.entity.User

/**
 * Created by hoang.van.tuan on 2/2/18.
 */

@Dao
abstract class UserDao {

    @Insert
    abstract fun insert(user: User)

    @Update
    abstract fun updateItem(user: User)

}
