package com.example.yugan.abc.ui.reports.user.complaint

interface ComplaintView {

    fun clickToFileComplaint()

    fun validateAddress():Boolean

    fun validateComplaint():Boolean

    fun validateAll():Boolean

    fun showAddressError()

    fun showComplaintError()

    fun showAllError()
}