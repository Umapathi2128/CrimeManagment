package com.example.yugan.abc.repository.room.complaint

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class ComplaintDataModel(
                        @PrimaryKey @field:ColumnInfo(name="email") var email: String,
                        @field:ColumnInfo(name = "complaint") var complaint: String,
                        @field:ColumnInfo(name = "complaintType") var complaintType: String,
                        @field:ColumnInfo(name = "complaintFor") var complaintFor : String ,
                        @field:ColumnInfo(name = "complaintAddress") var complaintAddress: String ,
                        @field:ColumnInfo(name = "policeName") var policeName: String,
                        @field:ColumnInfo(name="status") var status: Int)