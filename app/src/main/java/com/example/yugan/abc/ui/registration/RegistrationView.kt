package com.example.yugan.abc.ui.registration

/**
 * Created by yugan on 3/26/2018.
 */
interface RegistrationView {

    fun isValidName():Boolean

    fun isValidEmail():Boolean

    fun isValidDOb():Boolean

    fun isValidGender():Boolean

    fun isValidMobile():Boolean

    fun isValidAddres():Boolean

    fun isValidPassword():Boolean

    fun showNAmeError()

    fun showEmailError()

    fun showDOBError()

    fun showMobileError()

    fun showAddressError()

    fun showGenderError()

    fun showPasswordError()

    fun registration()
}