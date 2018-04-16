package com.example.yugan.abc.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.example.yugan.abc.repository.room.complaint.ComplaintDataModel
import com.example.yugan.abc.repository.room.complaint.ComplaintDatabase
import com.example.yugan.abc.repository.room.registrtion.UserDataBase
import com.example.yugan.abc.repository.room.registrtion.UserDataModel

class UserDetails {

    fun getAll(context: Context): List<UserDataModel> {

        val userDataBase: UserDataBase = Room.databaseBuilder(context, UserDataBase::class.java, "production")
                .allowMainThreadQueries()
                .build()
        val list: List<UserDataModel>
        list = userDataBase.userDao().getAll()
        return list
    }

    fun getAllComplaints(context: Context): List<ComplaintDataModel> {

        val complaintDatabase: ComplaintDatabase = Room.databaseBuilder(context, ComplaintDatabase::class.java, "complaint")
                .allowMainThreadQueries()
                .build()
        val list: List<ComplaintDataModel>
        list = complaintDatabase.complaintDao().getAllComplaints()
        return list
    }
}