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

        val url = "https://img4.mukewang.com/szimg/5d43953c09c0247612000676-228-128.png"
        Glide.with(this)
            .load(url)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 10)))
            .into(mMusicBg)

        mPlayMusic.setMusicCover(url)
        mPlayMusic.play("http://res.lgdsunday.club/Nostalgic%20Piano.mp3")
    }

    fun onBackClick (view: View) {
        onBackPressed()
    }
}
