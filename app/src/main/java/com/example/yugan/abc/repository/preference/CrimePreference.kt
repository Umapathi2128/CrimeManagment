package com.example.yugan.abc.repository.preference

import com.example.yugan.abc.repository.networks.StoreUserDataModel

interface CrimePreference {

    fun saveUsers(storeUserDataModel: StoreUserDataModel)

    fun getUsers():StoreUserDataModel
}