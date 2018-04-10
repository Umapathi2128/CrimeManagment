package com.example.yugan.abc

import android.annotation.SuppressLint
import android.support.design.widget.Snackbar
import android.view.View

class SnackBarClass {

    @SuppressLint("ResourceAsColor")
    fun snackShow(view: View, message:String) {
        val snackbar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)

        snackbar.setAction("Ok", View.OnClickListener {
            snackbar.dismiss()
        })
        snackbar.setActionTextColor(R.color.yellow)
        snackbar.show()
    }
}