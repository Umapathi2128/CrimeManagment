package com.example.yugan.abc

import android.content.Context
import android.widget.Toast

class ToastClass {

    fun show(context: Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}