package com.example.yugan.abc.ui.reports.user.complaint

class ComplaintViewModel(var complaintView: ComplaintView) {

    fun complaintFile(){

        if(!complaintView.validateAddress()) complaintView.showAddressError()
        else if(!complaintView.validateComplaint()) complaintView.showComplaintError()
        else if(!complaintView.validateAll()) complaintView.showAllError()
        else complaintView.clickToFileComplaint()   }
}