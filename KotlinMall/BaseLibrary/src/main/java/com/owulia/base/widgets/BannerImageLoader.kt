package com.owulia.base.widgets

import android.content.Context
import android.widget.ImageView
import com.owulia.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        GlideUtils.loadImage(context!!, path.toString(), imageView!!)
    }
}