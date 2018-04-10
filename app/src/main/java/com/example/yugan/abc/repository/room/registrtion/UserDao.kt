package com.example.yugan.abc.repository.room.registrtion

import android.arch.persistence.room.*


@Dao
interface UserDao {

    @Query("SELECT * FROM userDataModel")
    fun  getAll():MutableList<UserDataModel>

    @Insert
    fun insertAll(userDataModel: UserDataModel)

    @Delete
    fun deleteAll(userDataModel: UserDataModel)

    @Query("UPDATE userDataModel SET password= :password WHERE email= :email")
    fun updatePassword(email:String,password:String)

//    @Query("SELECT * FROM userdatamodel where name IN (:name)")
//    fun loginCheck(name:String,password:String):List<String>
}