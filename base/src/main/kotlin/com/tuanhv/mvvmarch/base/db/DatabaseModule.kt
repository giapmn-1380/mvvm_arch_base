package com.tuanhv.mvvmarch.base.db

import androidx.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import com.tuanhv.mvvmarch.base.db.user.UserDao
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by hoang.van.tuan on 2/2/18.
 */
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext application: Context): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "sample.db")
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

}
