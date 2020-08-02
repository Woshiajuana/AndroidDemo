package com.owulia.imoocmusicdemo.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.adapters.MusicGridAdapter
import com.owulia.imoocmusicdemo.adapters.MusicListAdapter
import com.owulia.imoocmusicdemo.views.GridSpaceItemDecoration
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
            isNestedScrollingEnabled = false
//            addItemDecoration(GridSpaceItemDecoration(resources.getDimensionPixelOffset(R.dimen.marginTabSize), this))
            layoutManager = GridLayoutManager(context, 3)
        }

        mMusicList.apply {
            adapter = MusicListAdapter(context, this)
            isNestedScrollingEnabled = false
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("onActivityResult:", "requestCode => $requestCode")
        Log.d("onActivityResult:", "resultCode => $resultCode")
        Log.d("onActivityResult:", "data => $data")

    }
}
