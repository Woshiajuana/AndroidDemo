package com.owulia.wowcool.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.owulia.wowcool.fragment.DemoFragment

class MainPagerAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return DemoFragment.instant
    }

    override fun getCount(): Int {
        return 1
    }
}