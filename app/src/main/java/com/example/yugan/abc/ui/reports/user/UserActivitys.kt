package com.example.yugan.abc.ui.reports.user

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.ToastClass
import com.example.yugan.abc.databinding.ActivityUsersComplaintBinding
import com.example.yugan.abc.repository.preference.CrimePreferenceHelper
import com.example.yugan.abc.ui.reports.user.UserDataModel
import com.example.yugan.abc.ui.reports.user.UserView
import com.example.yugan.abc.ui.reports.user.UserViewModel
import com.example.yugan.abc.ui.reports.user.complaint.ComplaintActivity
import com.example.yugan.abc.ui.reports.user.complaint.complaintView.ComplaintRattingActivity
import com.example.yugan.abc.ui.splash.SplashActicity

class UserActivitys :AppCompatActivity(), UserView {


    private lateinit var activityUsersComplaintBinding:ActivityUsersComplaintBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUsersComplaintBinding=DataBindingUtil.setContentView(this, R.layout.activity_users_complaint)

        val userViewModel= UserViewModel(this, UserDataModel("", "", "", "", ""))
        activityUsersComplaintBinding.setVariable(BR.complaint,userViewModel)

    }


    override fun fileComplaint() {
    animation(1,this)
    }

    override fun checkStatus() {
        animation(2,this)
    }

    override fun ratePolice() {
        animation(3,this)
    }

    override fun logout() {
        animation(5,this)
    }

    override fun complaintAgainstPolice() {
        animation(4,this)
    }

    /**
     *  Animations are done here...
     */
    private fun animation(position: Int, context: Context) {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_left)


        val animations: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_right)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                when (position) {
                    1 ->{
                        startActivity(Intent(context,ComplaintActivity::class.java))
                    }

                    3 -> {startActivity(Intent(context,ComplaintRattingActivity::class.java))}

                    5->{startActivity(Intent(context,SplashActicity::class.java))
                        finish()}
                    else -> return

                }
            }
        })
        animations.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                when (position) {
                    2 -> {ToastClass().show(context,"clicked status")
                    }
                    4->{ToastClass().show(context,"Rate against police...")
                        }
                    else -> return

                }
            }
        })
        activityUsersComplaintBinding.againstPolice.startAnimation(animation)
        activityUsersComplaintBinding.complaint.startAnimation(animation)
        activityUsersComplaintBinding.logout.startAnimation(animation)
        activityUsersComplaintBinding.status.startAnimation(animations)
        activityUsersComplaintBinding.ratting.startAnimation(animations)
    }
}