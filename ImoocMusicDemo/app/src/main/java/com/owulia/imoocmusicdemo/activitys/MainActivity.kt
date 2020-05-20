package com.owulia.imoocmusicdemo.activitys

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.adapters.MusicGridAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView () {
        initNavBar(false,"码可音乐", true)

        mMusicGird.apply {
            adapter = MusicGridAdapter(context)
            layoutManager = GridLayoutManager(context, 3)
        }

    }
}
