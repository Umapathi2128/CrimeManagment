package com.example.yugan.abc.ui.forgetten

class ForgettenViewModel(private var forgettenView: ForgettenView, var forgettenDataModel: ForgettenDataModel) {

    fun isClicked()
    {
        if(forgettenView.isValidEmail()) forgettenView.isClick()
    }
}