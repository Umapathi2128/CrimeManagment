package com.example.yugan.abc.ui.forgetten

import android.arch.persistence.room.Room
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.SnackBarClass
import com.example.yugan.abc.ToastClass
import com.example.yugan.abc.databinding.ActivityForgettenPasswordBinding
import com.example.yugan.abc.repository.UserDetails
import com.example.yugan.abc.repository.room.registrtion.UserDataBase
import com.example.yugan.abc.repository.room.registrtion.UserDataModel

class ForgettenPasswordActivity : AppCompatActivity(), ForgettenView {

    private lateinit var activityForgettenPasswordBinding: ActivityForgettenPasswordBinding
    private var isChecked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityForgettenPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgetten_password)

        dataBinding()

        activityForgettenPasswordBinding.shimmer.startShimmerAnimation()


    }

    /**
     *  This method for data binding and it initilzations...
     */
    fun dataBinding() {
        val forgettenDataModel = ForgettenDataModel("Email","Enter your Email","New Password", "Confirm Password",
                "Enter your Password", "Re-Enter Password")
        val forgettenViewModel = ForgettenViewModel(this, forgettenDataModel)

        activityForgettenPasswordBinding.setVariable(BR.forgetten, forgettenViewModel)
    }


    override fun isValidEmail(): Boolean {

        return true
    }

    override fun isClick() {

        if (activityForgettenPasswordBinding.etxtFgtnConfirmPass.visibility == View.VISIBLE) {

            if (activityForgettenPasswordBinding.forgettenEtxt.text.trim().toString()
                    == activityForgettenPasswordBinding.etxtFgtnConfirmPass.text.trim().toString()) {

                val userdatabase: UserDataBase = Room.databaseBuilder(this, UserDataBase::class.java, "production")
                        .allowMainThreadQueries()
                        .build()
                userdatabase.userDao().updatePassword(activityForgettenPasswordBinding.forgettenEtxt.text.trim().toString()
                        ,activityForgettenPasswordBinding.etxtFgtnConfirmPass.text.trim().toString())
                ToastClass().show(this,"changed")
            }

        } else {
            activityForgettenPasswordBinding.fgtnTxt.text = ""
            activityForgettenPasswordBinding.shimmer.visibility = View.INVISIBLE

            val list: MutableList<UserDataModel> = UserDetails().getAll(this) as MutableList<UserDataModel>


            if (list.size == 0) SnackBarClass().snackShow(activityForgettenPasswordBinding.etxtFgtnConfirmPass,"List size is zero :)")

            for (i in 0 until list.size) {
                if (activityForgettenPasswordBinding.forgettenEtxt.text.trim().toString() == list[i].email) isChecked=true
            }


            if (isChecked) {
                activityForgettenPasswordBinding.forgettenEtxt.text.clear()
                activityForgettenPasswordBinding.etxtFgtnConfirmPass.visibility = View.VISIBLE
                activityForgettenPasswordBinding.txtFgtnConfirmPass.visibility = View.VISIBLE
                activityForgettenPasswordBinding.fgtnTxt.text = ""
                isChecked = false

            } else {
                activityForgettenPasswordBinding.shimmer.visibility = View.VISIBLE
                val str = "Email Not Matched..."
                activityForgettenPasswordBinding.fgtnTxt.text = str

            }
        }
    }



}
