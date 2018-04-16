package com.example.yugan.abc.repository.room.policeDatabase

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
@Dao
interface PoliceDao {

    @Query("SELECT * FROM policeDataModel")
    fun getAllPoliceDetails():MutableList<PoliceDataModel>

    @Insert
    fun insertPoliceDetails(policeDataModel: PoliceDataModel)

}