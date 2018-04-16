package com.example.yugan.abc.ui.reports.user.complaint.complaintView

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.yugan.abc.BR
import com.example.yugan.abc.R
import com.example.yugan.abc.databinding.ActivityComplaintRattingBinding
import com.example.yugan.abc.repository.UserDetails
import com.example.yugan.abc.repository.preference.CrimePreferenceHelper
import com.example.yugan.abc.repository.room.complaint.ComplaintDataModel

class ComplaintRattingActivity : AppCompatActivity() ,ComplaintViewInterface{


    private lateinit var activityComplaintRattingBinding:ActivityComplaintRattingBinding
    lateinit var list:ArrayList<ComplaintDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_complaint_ratting)
        list=ArrayList()
        list=UserDetails().getAllComplaints(this) as ArrayList<ComplaintDataModel>
        val arrList=ArrayList<ComplaintDataModel>()



        for(i in 0 until list.size)
        {
            if (list[i].accust==CrimePreferenceHelper().getString("email",this))
            {
//
                var status=""
                when {
                    list[i].status == 0.toString() -> status="Complaint received"
                    list[i].status == 1.toString() -> status="Proceesing your complaint"
                    list[i].status == 2.toString() -> status="Solved the problem"
                    list[i].status == 3.toString() -> status="Complaint denied"
                }
                arrList.add(ComplaintDataModel(list[i].accust,list[i].complaint,list[i].adhaar,list[i].complaintAddress
                ,list[i].policeName,list[i].crimeTime,list[i].complaintTime,status,list[i].ratting))
            }
        }

        activityComplaintRattingBinding=DataBindingUtil.setContentView(this,R.layout.activity_complaint_ratting)

        val complaintViewViewModel=ComplaintViewViewModel(this)
        activityComplaintRattingBinding.setVariable(BR.complaint,complaintViewViewModel)

        val complaintViewAdapter=ComplaintViewAdapter(arrList,this)

       val recyclerView:RecyclerView.LayoutManager=LinearLayoutManager(applicationContext)
        activityComplaintRattingBinding.viewComplaintRecycler.layoutManager=recyclerView
        activityComplaintRattingBinding.viewComplaintRecycler.itemAnimator = DefaultItemAnimator()
        activityComplaintRattingBinding.viewComplaintRecycler.adapter = complaintViewAdapter

    }
    override fun onRecyclerClick() {

    }

}
