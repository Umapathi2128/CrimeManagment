package com.example.yugan.abc.repository.room.complaint

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class ComplaintDataModel(
                        @field:ColumnInfo(name="accust") var accust: String,
                        @PrimaryKey @field:ColumnInfo(name = "complaint") var complaint: String,
                        @field:ColumnInfo(name = "adhaar") var adhaar: String,
                        @field:ColumnInfo(name = "complaintAddress") var complaintAddress: String ,
                        @field:ColumnInfo(name = "policeName") var policeName: String,
                        @field:ColumnInfo(name = "crimeTime") var crimeTime: String,
                        @field:ColumnInfo(name = "complaintTime") var complaintTime : String ,
                        @field:ColumnInfo(name="status") var status: String,
                        @field:ColumnInfo(name = "ratting") var ratting:String)