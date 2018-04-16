package com.example.yugan.abc.ui.reports.user.complaint.complaintView

class ComplaintViewViewModel(var complaintViewInterface: ComplaintViewInterface) {

    fun onRecyclerClick(){
        complaintViewInterface.onRecyclerClick()
    }
}