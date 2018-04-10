package com.example.yugan.abc.repository.room.registrtion

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(UserDataModel::class),
        version = 2)
 abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}