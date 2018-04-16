package com.example.yugan.abc.repository.room.policeDatabase

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
class PoliceDataModel(
        @PrimaryKey @field:ColumnInfo(name = "policeId") var policeId:String,
        @field:ColumnInfo(name = "policeName") var policeName:String,
        @field:ColumnInfo(name = "ratting") var ratting:String)
