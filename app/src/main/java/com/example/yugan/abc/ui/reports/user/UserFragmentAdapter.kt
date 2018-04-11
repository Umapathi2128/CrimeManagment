package com.example.yugan.abc.ui.reports.user


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.yugan.abc.ui.reports.user.complaint.ComplaintFragment

class UserFragmentAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0-> return ComplaintFragment()
            1->return UserDetailsFragment()
            2->return UserDetailsFragment()
            3->return UserDetailsFragment()
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return 4
    }
}