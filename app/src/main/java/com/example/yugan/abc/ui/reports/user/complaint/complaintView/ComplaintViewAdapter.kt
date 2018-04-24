package com.example.yugan.abc.ui.reports.user.complaint.complaintView

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yugan.abc.R
import com.example.yugan.abc.databinding.ComplaintViewRecyclerBinding
import com.example.yugan.abc.repository.room.complaint.ComplaintDataModel

class ComplaintViewAdapter(private var list: ArrayList<ComplaintDataModel>, var context: Context) : RecyclerView.Adapter<ComplaintViewAdapter.MyViewHolder>() {

    private lateinit var recyclerBinding:ComplaintViewRecyclerBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        recyclerBinding=DataBindingUtil.inflate(LayoutInflater.from(context)
                ,R.layout.complaint_view_recycler, parent, false)

        return MyViewHolder(recyclerBinding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val complaint=": "+list[position].complaint
        val criminal=": "+list[position].accust
        val police=": "+list[position].policeName
        val ratting=": "+list[position].ratting
        val status=": "+list[position].status
    recyclerBinding.txtComplaint.text=complaint
    recyclerBinding.txtCriminal.text=criminal
    recyclerBinding.txtPolice.text=police
    recyclerBinding.txtRatting.text=ratting
    recyclerBinding.txtStatus.text= status
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener({
                val intent=Intent(itemView.context,GiveRattingActivity::class.java)
                itemView.context.startActivity(intent)
            })
        }
    }
}