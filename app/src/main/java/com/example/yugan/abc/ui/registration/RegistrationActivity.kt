@file:Suppress("DEPRECATION")

package com.example.yugan.abc.ui.registration

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.databinding.ActivityRegistrationBinding
import com.example.yugan.abc.repository.room.registrtion.UserDataBase
import com.example.yugan.abc.repository.room.registrtion.UserDataModel
import java.util.*
import android.app.ProgressDialog
import android.content.Context
import javax.mail.*
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.view.View
import com.example.yugan.abc.SnackBarClass
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class RegistrationActivity : AppCompatActivity(), RegistrationView {


     private lateinit var activityRegistrationBinding: ActivityRegistrationBinding
    private lateinit var progressDailouge:ProgressDialog
    var text :String=""
    private var isAvailabe=false
    private lateinit var value:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      value =intent.extras.getString("type")

        activityRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        val registationViewModel= RegistrationViewmModel(this,
                RegistrationModel("User Name", "Email", "Dob", "Gender", "Mobile"
                        , "Address", "Password", "Registration"))

        activityRegistrationBinding.setVariable(BR.register, registationViewModel)
    }

    override fun isValidName(): Boolean {

        return activityRegistrationBinding.extName.text.trim().toString() != ""
    }

    override fun isValidEmail(): Boolean {
        return activityRegistrationBinding.etxtEmail.text.trim().toString() != ""
    }

    override fun isValidDOb(): Boolean {
        return activityRegistrationBinding.etxtDob.text.trim().toString() != ""
    }

    override fun isValidGender(): Boolean {

        activityRegistrationBinding.rgButton.setOnCheckedChangeListener { _, checkedId ->

            text = when (checkedId) {
                R.id.rbtnMale -> "male"
                R.id.rbtnFemale -> "female"
                else -> "not"
            }
        }
        return when (text) {
            "male", "female" -> {
                activityRegistrationBinding.txtGenError.visibility= View.GONE
                true
            }
            else -> {
                activityRegistrationBinding.txtGenError.visibility= View.VISIBLE
                false
            }
        }
    }

    override fun isValidMobile(): Boolean {
        return activityRegistrationBinding.etxtMobile.text.trim().toString() != ""
    }

    override fun isValidAddres(): Boolean {
        return activityRegistrationBinding.etxtAddress.text.trim().toString() != ""
    }

    override fun showNAmeError() {
        activityRegistrationBinding.extName.error = "Name shouldn't be empty.."
    }

    override fun showEmailError() {
        activityRegistrationBinding.etxtEmail.error = "Email shouldn't be empty.."
    }

    override fun showDOBError() {
        activityRegistrationBinding.etxtDob.error = "Dob shouldn't be empty.."
    }

    override fun showMobileError() {
        activityRegistrationBinding.etxtMobile.error = "Mobile number shouldn't be empty.."
    }

    override fun showAddressError() {
        activityRegistrationBinding.etxtAddress.error = "Address shouldn't be empty.."
    }

    override fun showGenderError() {
        val str="Please check the Gender..."
        activityRegistrationBinding.txtGenError.text=str
    }

    override fun showPasswordError() {
        activityRegistrationBinding.etxtPassword.error="Passowrd shouldn't be empty..."
    }

    override fun isValidPassword(): Boolean {
        return activityRegistrationBinding.etxtPassword.text.trim().toString()!=""
    }


    override fun registration() {
        val database: UserDataBase = Room.databaseBuilder(applicationContext, UserDataBase::class.java, "production")
                                                            .allowMainThreadQueries()
                                                            .build()
        val list=database.userDao().getAll()

        for (i in 0 until list.size){
            isAvailabe=false
            if (list[i].email==activityRegistrationBinding.etxtEmail.text.trim().toString()){
               isAvailabe=true
            }
        }
        val txt="The Email is already exists..."
        when {

            isAvailabe -> {
                activityRegistrationBinding.txtGenError.visibility= View.VISIBLE
                activityRegistrationBinding.txtGenError.text=txt
            }

            isNetworkCheck() -> {
                database.userDao().insertAll(UserDataModel(activityRegistrationBinding.extName.text.toString()
                        , activityRegistrationBinding.etxtEmail.text.toString()
                        , activityRegistrationBinding.etxtDob.text.toString()
                        , text
                        , activityRegistrationBinding.etxtMobile.text.toString()
                        , activityRegistrationBinding.etxtAddress.text.toString()
                        , activityRegistrationBinding.etxtPassword.text.toString()
                        , value))

//                startActivity(Intent(this, LoginActivity::class.java))
                sendingMail()
            }
            else -> SnackBarClass().snackShow(activityRegistrationBinding.btnRegister,"No Internet Connection..")
        }

    }


    /**
     *  Sending mail with using smtp...
     */
    private fun sendingMail() {
        val userEmail="uma@gmail.com"
        val password="password"
        val props = Properties()
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "587"

        val session = Session.getInstance(props, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
                return PasswordAuthentication(userEmail, password)
            }
        })
        progressDailouge=ProgressDialog.show(this,"","Sendin Message...",true)
       val mailAsynk= MailAsynk(session, progressDailouge, this, activityRegistrationBinding.etxtEmail.text.trim().toString())
        mailAsynk.execute()
    }

    private fun isNetworkCheck():Boolean{
        val connectivityManager:ConnectivityManager= getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected
    }


     class MailAsynk(private var session: Session, private var prog: ProgressDialog, @SuppressLint("StaticFieldLeak") val context: Context, var toEmailAddress: String) : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String? {
            val message = MimeMessage(session)
            try {
                message.setFrom(InternetAddress("uma@gmail.com"))
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress))
                message.subject = "hii uma garu"
//                message.setContent("hello uma", "text")
                Transport.send(message)
            } catch (e: MessagingException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
                return message.toString()
        }

        override fun onPostExecute(result: String) {
        prog.dismiss()
        Toast.makeText(context, "Message sent", Toast.LENGTH_LONG).show()
        }
    }
}
