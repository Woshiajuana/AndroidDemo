package com.owulia.imoocmusicdemo.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.owulia.imoocmusicdemo.R
import kotlinx.android.synthetic.main.play_music.view.*

class PlayMusicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        Log.d("PlayMusicView", "context => ${context}")
        Log.d("PlayMusicView", "this => ${this}")
//        val mView = LayoutInflater.from(context).inflate(R.layout.play_music, this, false)
//        addView(mView)
    }

    fun setMusicCover () {
        Glide.with(this)
            .load("https://img4.mukewang.com/szimg/5d43953c09c0247612000676-228-128.png")
            .into(this.mCoverImage)
    }

}