package com.tuanhv.mvvmarch.base.db

import androidx.room.Room
import com.tuanhv.mvvmarch.base.db.user.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
val databaseModule = module {
    /**
     * provide [AppDatabase]
     */
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "sample.db")
                .allowMainThreadQueries()
                .build()
    }

    /**
     * provide [UserDao]
     */
    single { get<AppDatabase>().userDao() }
}
