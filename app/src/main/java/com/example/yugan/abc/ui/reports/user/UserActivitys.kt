package com.example.yugan.abc.ui.reports.user

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.ToastClass
import com.example.yugan.abc.databinding.ActivityUsersComplaintBinding
import com.example.yugan.abc.ui.reports.user.UserDataModel
import com.example.yugan.abc.ui.reports.user.UserView
import com.example.yugan.abc.ui.reports.user.UserViewModel

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
        animation(4,this)
    }

    override fun complaintAgainstPolice() {
        animation(5,this)
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
                        ToastClass().show(context,"clicked file a complaint")
                    }

                    3 -> {ToastClass().show(context,"clicked rate police")}

                    5->{ToastClass().show(context,"clicked police")}
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
                    4->{ToastClass().show(context,"clicked logout")}
                    else -> return

                }
            }
        })
    }
}