package com.example.yugan.abc.ui.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.VISIBLE
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.SnackBarClass
import com.example.yugan.abc.databinding.ActivityMainBinding
import com.example.yugan.abc.repository.UserDetails
import com.example.yugan.abc.repository.preference.CrimePreferenceHelper
import com.example.yugan.abc.repository.room.registrtion.UserDataModel
import com.example.yugan.abc.ui.forgetten.ForgettenPasswordActivity
import com.example.yugan.abc.ui.registration.RegistrationActivity
import com.example.yugan.abc.ui.reports.police.PoliceActivity
import com.example.yugan.abc.ui.reports.user.UserActivitys

class LoginActivity : AppCompatActivity(), LoginView {


    private lateinit var activityMainBainding: ActivityMainBinding
    lateinit var value: String
    private lateinit var arrList: ArrayList<UserDataModel>
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBainding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //  getting shared preference value...
        value = CrimePreferenceHelper().getString("type", this)


        val loginViewModel = LoginViewModel(this,
                LoginModel("$value Name", "Password", "Reset", "Login"))
        activityMainBainding.setVariable(BR.login, loginViewModel)
    }


    override fun isValidUser(): Boolean {
        return activityMainBainding.extUser.text.trim().toString() != ""

    }

    override fun isValidPassword(): Boolean {
        return activityMainBainding.extPass.text.trim().toString() != ""
    }

    override fun showUserError() {
        activityMainBainding.extUser.error = "User Should not be empty.."
    }

    override fun showPasswordError() {
        activityMainBainding.extPass.error = "Password Should not be empty.."
    }

    override fun reset() {
        activityMainBainding.extUser.text = null
        activityMainBainding.extPass.text = null
    }

    /**
     *  this methos for getting details from room database and validate the fields and login..
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun login() {
        arrList = UserDetails().getAll(this) as ArrayList<UserDataModel>

        if (arrList.isNotEmpty()) {

            for (i in 0 until arrList.size) {
                activityMainBainding.txtLogin.visibility = View.GONE

                if (activityMainBainding.extUser.text.trim().toString() == arrList[i].name
                        && activityMainBainding.extPass.text.trim().toString() == arrList[i].password
                        && value == arrList[i].type) {

                    //setting current email to the shared preference....
                    CrimePreferenceHelper().putString(this, "email", arrList[i].email)
                    CrimePreferenceHelper().putString(this, "adhaar", arrList[i].adhaar)
                    isChecked = true

                }

            }
            if (isChecked) {
                isChecked = false
                when (value) {
                    "Admin" -> {
                        startActivity(Intent(
                                this,
                                UserActivitys::class.java
                        ))
                        finish()
                    }
                    "Police" -> {
                        startActivity(Intent(this, PoliceActivity::class.java))
                        finish()
                    }
                    "User" -> {
                        startActivity(Intent(this, UserActivitys::class.java))
                        finish()
                    }
                }

            } else {
                activityMainBainding.txtLogin.visibility = VISIBLE
                val str = "Plaese Enter valid Details..."
                activityMainBainding.txtLogin.text = str
            }
        } else SnackBarClass().snackShow(activityMainBainding.btnLogin, "DataBase is Empty...")


    }

    /**
     *  This method for redirecting to the registration activity...
     */
    override fun newUser() {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }

    /**
     *  This method for redirecting to the forgetten password activity...
     */
    override fun forgettenPassword() {
        startActivity(Intent(this, ForgettenPasswordActivity::class.java))
    }
}
