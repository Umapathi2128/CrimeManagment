package com.example.yugan.abc.repository.room.complaint

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface ComplaintDao {

    @Query("SELECT * FROM  complaintDataModel")
    fun getAllComplaints():MutableList<ComplaintDataModel>

    @Insert
    fun insertComplaints(complaintDataModel: ComplaintDataModel)

    @Delete
    fun deleteComplaint(complaintDataModel: ComplaintDataModel)
}