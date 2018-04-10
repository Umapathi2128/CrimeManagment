package com.example.yugan.abc.ui.login

/**
 * Created by yugan on 3/25/2018.
 */
class LoginViewModel(private var loginView: LoginView, var loginModel: LoginModel) {


    /**
     *  This method for reset all the values..
     */
    fun reset()
    {
        loginView.reset()
    }

    /**
     *  This method for login function....
     */
    fun login()
    {
        if(!loginView.isValidUser()) loginView.showUserError()
        else if(!loginView.isValidUser()) loginView.showPasswordError()
        else loginView.login()
    }

    /**
     *  This method for moving to registration activity...
     */
    fun newUser()
    {
        loginView.newUser()
    }

    fun forgettenPassword()
    {
        loginView.forgettenPassword()
    }
}