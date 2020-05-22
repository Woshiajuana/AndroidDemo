package com.owulia.imoocmusicdemo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.owulia.imoocmusicdemo.R
import com.owulia.imoocmusicdemo.helps.MediaPlayerHelp
import kotlinx.android.synthetic.main.play_music.view.*

class PlayMusicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val _mediaPlayerHelp : MediaPlayerHelp
    private var _animPlayMusic : Animation
    private var _animPlayNeedle : Animation
    private var _animStopNeedle : Animation
    private var isPlaying = true
    private var _path : String? = null

    init {
        addView(LayoutInflater.from(context).inflate(R.layout.play_music, this, false))

        _mediaPlayerHelp = MediaPlayerHelp.getInstance(context)

        _animPlayMusic = AnimationUtils.loadAnimation(context, R.anim.play_music_anim)
        _animPlayNeedle = AnimationUtils.loadAnimation(context, R.anim.play_needle_anim)
        _animStopNeedle = AnimationUtils.loadAnimation(context, R.anim.stop_needle_anim)

        mCDMusic.setOnClickListener {
            if (isPlaying) stop() else play(_path)
        }

//        play()

    }

    fun setMusicCover (url: String) {
        Glide.with(this)
            .load(url)
            .into(this.mCoverImage)

    }

    // 定义所需要执行的动画
    private fun stop () {
        isPlaying = false
        mPlayBtn.visibility = View.VISIBLE
        mCDMusic.clearAnimation()
        mNeedleMusic.startAnimation(_animStopNeedle)
        _mediaPlayerHelp.pause()
    }

    private fun play (path: String) {
        isPlaying = true
        mPlayBtn.visibility = View.GONE
        mCDMusic.startAnimation(_animPlayMusic)
        mNeedleMusic.startAnimation(_animPlayNeedle)

        if (_mediaPlayerHelp.getPath() == path) {
            _mediaPlayerHelp.start()
        } else {
            _mediaPlayerHelp.setPath(path)
        }
    }

}