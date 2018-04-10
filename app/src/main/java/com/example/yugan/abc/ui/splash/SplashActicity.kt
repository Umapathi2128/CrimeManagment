package com.example.yugan.abc.ui.splash

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.databinding.SplashBinding
import com.example.yugan.abc.ui.login.LoginActivity

class SplashActicity : AppCompatActivity(), SplashView {

    private lateinit var splashBinding: SplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash_acticity)
        splashBinding = DataBindingUtil.setContentView(this, R.layout.splash)

        val splashDataModel = SplashDataModel("Admin", "User", "Police")
        val splashViewModel = ViewModel(this, splashDataModel)
        splashBinding.setVariable(BR.abc, splashViewModel)
    }

    override fun adminLogin() {
        animation(1, this)
    }

    override fun userLogin() {
        animation(2, this)
    }

    override fun policeLogin() {
        animation(3, this)
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
                        val intent1=Intent(context, LoginActivity::class.java)
                        intent1.putExtra("type",1)
                        startActivity(intent1)
                    }

                    3 -> {
                        val intent3=Intent(context, LoginActivity::class.java)
                        intent3.putExtra("type",3)
                        startActivity(intent3)
                    }

                    else -> return

                }
            }
        })
        animations.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                when (position) {
                    2 -> {
                        val intent2=Intent(context, LoginActivity::class.java)
                        intent2.putExtra("type",2)
                        startActivity(intent2)
                    }
                    else -> return

                }
            }
        })
        splashBinding.btnUser.startAnimation(animations)
        splashBinding.btnAdmin.startAnimation(animation)
        splashBinding.btnPolice.startAnimation(animation)
    }
}
