package com.owulia.wechattabbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.owulia.wechattabbar.views.HomeFragment
import com.owulia.wechattabbar.views.MaterialFragment
import com.owulia.wechattabbar.views.MineFragment
import com.owulia.wechattabbar.views.TabView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val mTitles = ArrayList<String>(listOf("码可", "有料", "我的"))

    private var mTabs = ArrayList<TabView>()

    private var mFragment = SparseArray<Fragment>()


//    val x = 10 downTo  1 until 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnHome.setIconAndText(R.mipmap.tab_bar_home_normal, R.mipmap.tab_bar_home_active, "码可")
        btnMaterial.setIconAndText(R.mipmap.tab_bar_material_normal, R.mipmap.tab_bar_material_active, "有料")
        btnMine.setIconAndText(R.mipmap.tab_bar_mine_normal, R.mipmap.tab_bar_mine_active, "我的")

        btnHome.setProgress(1f)

        btnHome.setOnClickListener {
            switchTab(0)
            mMain.setCurrentItem(0, false)
        }
        btnMaterial.setOnClickListener {
            switchTab(1)
            mMain.setCurrentItem(1, false)
        }
        btnMine.setOnClickListener {
            switchTab(2)
            mMain.setCurrentItem(2, false)
        }

        mTabs.add(btnHome)
        mTabs.add(btnMaterial)
        mTabs.add(btnMine)

        initViewPagerAdapter()
    }

    private fun switchTab (pos: Int) {
//        mTabs.forEachIndexed { index, tabView ->  }
        for (i in 0 until mTabs.size) {
            var mTab = mTabs[i]
            if (pos == i) {
                mTab.setProgress(1f)
            } else {
                mTab.setProgress(0f)
            }
        }
    }

    private fun initViewPagerAdapter () {
        mMain.apply {
            offscreenPageLimit = mTitles.size
            adapter = object : FragmentPagerAdapter(supportFragmentManager){
                override fun getItem(position: Int): Fragment {
                    return when (position) {
                        0 -> HomeFragment()
                        1 -> MaterialFragment()
                        else -> MineFragment()
                    }
                }
                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    val fragment =  super.instantiateItem(container, position) as Fragment
                    mFragment.put(position, fragment)
                    return fragment
                }
                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    mFragment.remove(position)
                    super.destroyItem(container, position, `object`)
                }
                override fun getCount(): Int {
                    return mTitles.size
                }
            }
            addOnPageChangeListener(object : OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {
                }
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (positionOffset > 0) {
                        val left = mTabs[position]
                        val right = mTabs[position + 1]
                        left.setProgress(1 - positionOffset)
                        right.setProgress(positionOffset)
                    }
                }
                override fun onPageSelected(position: Int) {
                }
            })
        }

    }


}
