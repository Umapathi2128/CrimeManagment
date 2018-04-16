package com.example.yugan.abc.ui.reports.user.complaint

import android.arch.persistence.room.Room
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.SnackBarClass
import com.example.yugan.abc.databinding.ActivityComplaintBinding
import com.example.yugan.abc.repository.UserDetails
import com.example.yugan.abc.repository.preference.CrimePreferenceHelper
import com.example.yugan.abc.repository.room.complaint.ComplaintDataModel
import com.example.yugan.abc.repository.room.complaint.ComplaintDatabase
import com.example.yugan.abc.ui.reports.user.UserActivitys
import java.util.*
import kotlin.collections.ArrayList

class ComplaintActivity : AppCompatActivity(), ComplaintView {

    private lateinit var activityComplaintBinding: ActivityComplaintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComplaintBinding = DataBindingUtil.setContentView(this, R.layout.activity_complaint)

        val complaintViewModel = ComplaintViewModel(this)
        activityComplaintBinding.setVariable(BR.uma, complaintViewModel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun clickToFileComplaint() {

        // getting all the data from user database...
        val list = UserDetails().getAll(this)

                    val policeList = ArrayList<String>()
                    var policeName = ""
                    for (i in 0 until list.size) {
                        if (list[i].type == "Police") policeList.add(list[i].name!!)
                    }

                    // setting police to the complaint randomly...
                    if (policeList.size == 0) SnackBarClass().snackShow(activityComplaintBinding.etxtAddress, "No Police Is Registered...")
                    else policeName = policeList[(Math.random() * policeList.size - 1).toInt()]

                    //inserting complaints to the complaint database.....
                    val complaintDatabase = Room.databaseBuilder(this, ComplaintDatabase::class.java, "complaint")
                            .allowMainThreadQueries().build()

                    complaintDatabase.complaintDao().insertComplaints(ComplaintDataModel(
                            CrimePreferenceHelper().getString("email", this)
                            , activityComplaintBinding.etxtComplaint.text.trim().toString()
                            , CrimePreferenceHelper().getString("adhaar", this)
                            , activityComplaintBinding.etxtAddress.text.trim().toString()
                            , policeName
                            , CrimePreferenceHelper().getString("type", this)
                            , Date(System.currentTimeMillis()).toString()
                            , "0"
                            , "0"
                    ))

                    SnackBarClass().snackShow(activityComplaintBinding.etxtAddress, "Inserted...")
                    startActivity(Intent(this, UserActivitys::class.java))
    }

    override fun validateAddress(): Boolean {
        return activityComplaintBinding.etxtAddress.text.trim().toString() != ""
    }

    override fun validateComplaint(): Boolean {
        return activityComplaintBinding.etxtComplaint.text.trim().toString() != ""
    }

    override fun validateAll(): Boolean {
        return CrimePreferenceHelper().getString("email", this) != "" &&
                CrimePreferenceHelper().getString("type", this) != ""
    }

    override fun showAddressError() {
        activityComplaintBinding.etxtAddress.error = "Please enter your Address"
    }

    override fun showComplaintError() {
        activityComplaintBinding.etxtComplaint.error = "Please enter your Complaint"
    }

    override fun showAllError() {
        activityComplaintBinding.showAllError.text = "Please Check email and type..."
    }
}
