package com.example.yugan.abc.repository.room.policeDatabase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(PoliceDataModel::class),version = 1)
abstract class PoliceDatabase: RoomDatabase() {

    abstract fun policeDao():PoliceDao
}