package com.example.yugan.abc.repository.room.complaint

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = arrayOf(ComplaintDataModel::class),version = 1)
abstract class ComplaintDatabase:RoomDatabase() {
    abstract fun complaintDao():ComplaintDao
}