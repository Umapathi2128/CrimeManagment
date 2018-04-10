package com.example.yugan.abc.ui.login

/**
 * Created by yugan on 3/25/2018.
 */
interface LoginView {

    fun isValidUser():Boolean

    fun isValidPassword():Boolean

    fun showUserError()

    fun showPasswordError()

    fun reset()

    fun login()

    fun newUser()

    fun forgettenPassword()

}