package com.example.yugan.abc.repository.preference

import android.content.Context
import android.content.SharedPreferences

class CrimePreferenceHelper {

    private var NAME:String="CRIME_USER_STORE"

    /**
     *  This method for Creating shared preference object...
     */
    private fun getPref(context: Context):SharedPreferences{
        return context.getSharedPreferences(NAME,Context.MODE_PRIVATE)
    }

    /**
     *  This method for putting the String to shared preference..
     */
    fun putString(context: Context,key:String,value:String)
    {
        var editor=getPref(context).edit().putString(key,value).commit()
    }

    /**
     *  This method for getting the string in the shared preference...
     */
    fun getString(key:String,context: Context):String{
        return getPref(context).getString(key,"")
    }

}