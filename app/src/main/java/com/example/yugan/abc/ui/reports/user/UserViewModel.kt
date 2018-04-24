package com.example.yugan.abc.ui.reports.user

class UserViewModel(var userView: UserView, var userDataModel: UserDataModel) {

    fun fileComplaint() {
        userView.fileComplaint()
    }

    fun checkStatus() {
        userView.checkStatus()
    }

    fun ratePolice() {
        userView.ratePolice()
    }

    fun complaintAgainstPolice() {
        userView.complaintAgainstPolice()
    }

    fun logout() {
        userView.logout()
    }


}