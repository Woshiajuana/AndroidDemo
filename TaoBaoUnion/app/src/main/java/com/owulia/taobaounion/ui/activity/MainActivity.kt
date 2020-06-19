package com.owulia.taobaounion.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.ui.fragment.HomeFragment
import com.owulia.taobaounion.ui.fragment.RedPackerFragment
import com.owulia.taobaounion.ui.fragment.SearchFragment
import com.owulia.taobaounion.ui.fragment.SelectedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mHomeFragment: HomeFragment
    private lateinit var mSelectedFragment: SelectedFragment
    private lateinit var mRedPackerFragment: RedPackerFragment
    private lateinit var mSearchFragment: SearchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initListener()
    }

    private fun initFragment () {
        mHomeFragment = HomeFragment()
        mSelectedFragment = SelectedFragment()
        mRedPackerFragment = RedPackerFragment()
        mSearchFragment = SearchFragment()
        switchFragment (mHomeFragment)
    }

    private fun initListener () {
        navBar.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.home -> switchFragment (mHomeFragment)
                R.id.selected -> switchFragment (mSelectedFragment)
                R.id.red_packet -> switchFragment (mRedPackerFragment)
                R.id.search -> switchFragment (mSearchFragment)
            }
            true
        }
    }

    private var lastOneFragment: BaseFragment? = null

    private fun switchFragment (targetFragment: BaseFragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (!targetFragment.isAdded) {
            fragmentTransaction.add(R.id.main_page_container, targetFragment)
        } else {
            fragmentTransaction.show(targetFragment)
        }
        if (lastOneFragment != null) {
            fragmentTransaction.hide(lastOneFragment!!)
        }
        lastOneFragment = targetFragment
        fragmentTransaction.commit()
    }

}
