package com.owulia.imoocmusicdemo.helps

import android.content.Context

class MediaPlayerHelp private constructor(context: Context) {

    companion object {

        private var instance : MediaPlayerHelp ?= null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            MediaPlayerHelp(context).also { instance = it }
        }

    }

}
