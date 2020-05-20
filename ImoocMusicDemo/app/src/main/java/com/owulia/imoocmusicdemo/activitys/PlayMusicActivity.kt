package com.owulia.imoocmusicdemo.activitys

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.owulia.imoocmusicdemo.R
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_play_music.*

class PlayMusicActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_music)

        window.addFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        Glide.with(this)
            .load("https://img4.mukewang.com/szimg/5d43953c09c0247612000676-228-128.png")
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 10)))
            .into(mMusicBg)
    }

    fun onBackClick (view: View) {
        onBackPressed()
    }
}
