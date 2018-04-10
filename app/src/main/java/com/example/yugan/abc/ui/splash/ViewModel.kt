package com.example.yugan.abc.ui.splash

class ViewModel(private var splashView: SplashView, splashDataModel: SplashDataModel) {

    var admin=splashDataModel.admin
    var user=splashDataModel.user
    var police=splashDataModel.police

    /**
     *  This method for admin login page..
     */
    fun adminLogin() {
        splashView.adminLogin()
    }

    /**
     *  This method for user login page..
     */
    fun userLogin() {
        splashView.userLogin()
    }

    /**
     *  This method for police login page..
     */
    fun policeLogin() {
        splashView.policeLogin()
    }
}