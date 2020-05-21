package com.owulia.imoocmusicdemo.helps

import android.content.Context

class MediaPlayerHelp private constructor() {

    companion object {

        private var instant : MediaPlayerHelp ?= null

        private fun getInstance() {

        }

//        val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            MediaPlayerHelp(context)
//        }
    }

}


class x {
    init {
        MediaPlayerHelp.getInstance()
    }
}
//
//
//class VolleySingleton private constructor(context: Context) {
//    companion object {
//        private var INSTANCE : VolleySingleton?=null
//        fun getInstance(context: Context) =
//            INSTANCE?: synchronized(this) {
//                VolleySingleton(context).also { INSTANCE = it }
//            }
//    }
//
//    val requestQueue: RequestQueue by lazy {
//        Volley.newRequestQueue(context.applicationContext);
//    }
//}