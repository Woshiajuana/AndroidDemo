package com.owulia.mall.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.owulia.mall.R
import com.owulia.mall.ui.fragment.HomeFragment
import com.owulia.mall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val mStack = Stack<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { HomeFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

        Observable.timer(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(){
                mBottomNavBar.checkMsgBadge(true)
            }

        initFragment()
        initBottomNav()
        changeFragment(0)
    }

    private fun initFragment () {
        val manager = supportFragmentManager.beginTransaction().apply {
            add(R.id.mContainer, mHomeFragment)
            add(R.id.mContainer, mCategoryFragment)
            add(R.id.mContainer, mCartFragment)
            add(R.id.mContainer, mMsgFragment)
            add(R.id.mContainer, mMeFragment)
            commit()
        }
        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }

    private fun initBottomNav () {
        mBottomNavBar.setTabSelectedListener(object: BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
            }
            override fun onTabUnselected(position: Int) {
            }
            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack) {
            manager.hide(fragment)
        }
        manager.show(mStack[position])
        manager.commit()
    }
}
