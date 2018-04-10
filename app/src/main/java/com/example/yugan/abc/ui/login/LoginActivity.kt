package com.example.yugan.abc.ui.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.SnackBarClass
import com.example.yugan.abc.databinding.ActivityMainBinding
import com.example.yugan.abc.repository.UserDetails
import com.example.yugan.abc.repository.room.registrtion.UserDataModel
import com.example.yugan.abc.ui.forgetten.ForgettenPasswordActivity
import com.example.yugan.abc.ui.registration.RegistrationActivity

@Suppress("UNREACHABLE_CODE")
class LoginActivity : AppCompatActivity(), LoginView {


    private lateinit var activityMainBainding: ActivityMainBinding
    lateinit var value: String
    private lateinit var arrList: ArrayList<UserDataModel>
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBainding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        value = intent.extras.getInt("type").toString()

        val txt: String = when (value) {
            "1" -> "Admin"
            "2" -> "User"
            "3" -> "Police"
            else -> ""
        }
        val loginViewModel = LoginViewModel(this,
                LoginModel("$txt Name", "Password", "Reset", "Login"))
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
     *  this methos for login..
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun login() {
        arrList = UserDetails().getAll(this) as ArrayList<UserDataModel>
        if (arrList.isNotEmpty()) {
            for (i in 0 until arrList.size) {
                activityMainBainding.txtLogin.visibility = View.GONE

                if (activityMainBainding.extUser.text.trim().toString() == arrList[i].name
                        && activityMainBainding.extPass.text.trim().toString() == arrList[i].password
                        && value == arrList[i].type)
                    isChecked = true
            }
            if (isChecked) {
                isChecked = false
                Toast.makeText(this, "succcess", Toast.LENGTH_LONG).show()
            } else {
                activityMainBainding.txtLogin.visibility = VISIBLE
                val str = "Plaese Enter valid Details..."
                activityMainBainding.txtLogin.text = str
            }
        } else SnackBarClass().snackShow(activityMainBainding.btnLogin,"DataBase is Empty...")


    }

//


    override fun newUser() {
        var intent = Intent(this, RegistrationActivity::class.java)
        intent.putExtra("type", value)
        startActivity(intent)
    }

    override fun forgettenPassword() {
        startActivity(Intent(this, ForgettenPasswordActivity::class.java))
    }
}
