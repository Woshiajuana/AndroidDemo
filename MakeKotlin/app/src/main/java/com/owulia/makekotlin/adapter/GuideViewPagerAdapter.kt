package com.owulia.makekotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.owulia.makekotlin.bean.GuidePageBean
import com.owulia.makekotlin.ui.fragment.GuidePageFragment

class GuideViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val mArrData: Array<GuidePageBean>
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return GuidePageFragment.getInstant(mArrData[position])
    }

    override fun getCount(): Int {
        return mArrData.size
    }
}