package com.example.yugan.abc.repository.room.registrtion

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class UserDataModel(@field:ColumnInfo(name = "name") var name: String?
                        ,@PrimaryKey @field:ColumnInfo(name = "email") var email:String
                        ,@field:ColumnInfo(name = "dob") var dob:String
                        ,@field:ColumnInfo(name = "gender") var gender:String
                        ,@field:ColumnInfo(name = "mobile") var mobile:String
                        ,@field:ColumnInfo(name = "address") var address:String
                        ,@field:ColumnInfo(name = "password") var password:String
                        ,@field:ColumnInfo(name = "type") var type:String)