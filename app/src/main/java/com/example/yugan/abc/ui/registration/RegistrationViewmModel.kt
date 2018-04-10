package com.example.yugan.abc.ui.registration

/**
 * Created by yugan on 3/26/2018.
 */
class RegistrationViewmModel(private var registrationView: RegistrationView, var registrationModel: RegistrationModel) {

    /**
     *  This method for validate all the feilds and register the user...
     */
    fun registration() {
        if (!registrationView.isValidName()) registrationView.showNAmeError()
        else if (!registrationView.isValidEmail()) registrationView.showEmailError()
        else if (!registrationView.isValidDOb()) registrationView.showDOBError()
        else if (!registrationView.isValidGender()) registrationView.showGenderError()
        else if (!registrationView.isValidMobile()) registrationView.showMobileError()
        else if (!registrationView.isValidAddres()) registrationView.showAddressError()
        else if (!registrationView.isValidPassword()) registrationView.showPasswordError()
        else registrationView.registration()
    }
}