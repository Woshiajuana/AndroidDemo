package com.owulia.imoocmusicdemo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.owulia.imoocmusicdemo.R
import kotlinx.android.synthetic.main.play_music.view.*

class PlayMusicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        addView(LayoutInflater.from(context).inflate(R.layout.play_music, this, false))
    }

    fun setMusicCover (url: String) {
        Glide.with(this)
            .load(url)
            .into(this.mCoverImage)
    }

}