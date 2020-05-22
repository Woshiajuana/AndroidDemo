package com.owulia.imoocmusicdemo.helps

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class MediaPlayerHelp private constructor(private val context: Context) {

    private val _mediaPlayer : MediaPlayer ?

    private var _path : String? = null

    init {
        _mediaPlayer = MediaPlayer()
    }

    companion object {

        private var instance : MediaPlayerHelp ?= null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            MediaPlayerHelp(context).also { instance = it }
        }

    }

    fun setPath (path: String) {
        _path = path
        // 音乐正在播放，重置播放状态
        if (_mediaPlayer!!.isPlaying) {
            _mediaPlayer.reset()
        }
        // 设置音乐的播放路径
        _mediaPlayer.setDataSource(context, Uri.parse(path))
        // 准备播放
        _mediaPlayer.prepareAsync()
        _mediaPlayer.setOnPreparedListener {

        }
    }

    fun getPath () = _path ?: ""

    // 播放音乐
    fun start () {
        if (_mediaPlayer!!.isPlaying) return
        _mediaPlayer.start()
    }

    // 暂停音乐
    fun pause () {
        _mediaPlayer!!.pause()
    }

    interface OnMediaPlayerHelperListener {
        fun onPrepared(mediaPlayer: MediaPlayer)
    }

}
