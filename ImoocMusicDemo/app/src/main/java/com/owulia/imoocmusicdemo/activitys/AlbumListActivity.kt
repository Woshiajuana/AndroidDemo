package com.owulia.imoocmusicdemo.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.adapters.MusicListAdapter
import kotlinx.android.synthetic.main.activity_album_list.*

class AlbumListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)

        initView()
    }

    private fun initView () {
        initNavBar(true, "专辑列表", false)

        mMusicList.apply {
            adapter = MusicListAdapter(context, null)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
        }

    }
}
