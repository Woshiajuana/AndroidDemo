package com.owulia.makekotlin.adapter

import android.net.Uri
import android.os.Looper
import android.text.TextUtils
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.apache.weex.WXEnvironment
import org.apache.weex.WXSDKManager
import org.apache.weex.adapter.IWXImgLoaderAdapter
import org.apache.weex.common.WXImageStrategy
import org.apache.weex.dom.WXImageQuality

class ImageAdapter : IWXImgLoaderAdapter {
    fun ImageAdapter() {}

    override fun setImage(
        url: String, view: ImageView?,
        quality: WXImageQuality?, strategy: WXImageStrategy
    ) {
        val runnable = Runnable {
            if (view == null || view.layoutParams == null) {
                return@Runnable
            }
            if (TextUtils.isEmpty(url)) {
                view.setImageBitmap(null)
                return@Runnable
            }
            var temp = url
            if (url.startsWith("//")) {
                temp = "http:$url"
            }
            if (view.layoutParams.width <= 0 || view.layoutParams.height <= 0) {
                return@Runnable
            }
            if (!TextUtils.isEmpty(strategy.placeHolder)) {
                val builder = Picasso.Builder(WXEnvironment.getApplication())
                val picasso = builder.build()
                picasso.load(Uri.parse(strategy.placeHolder)).into(view)
                view.setTag(strategy.placeHolder.hashCode(), picasso)
            }
            Picasso.with(WXEnvironment.getApplication())
                .load(temp)
                .transform(BlurTransformation(strategy.blurRadius))
                .into(view, object : Callback {
                    override fun onSuccess() {
                        if (strategy.imageListener != null) {
                            strategy.imageListener.onImageFinish(url, view, true, null)
                        }
                        if (!TextUtils.isEmpty(strategy.placeHolder)) {
                            (view.getTag(strategy.placeHolder.hashCode()) as Picasso).cancelRequest(
                                view
                            )
                        }
                    }

                    override fun onError() {
                        if (strategy.imageListener != null) {
                            strategy.imageListener.onImageFinish(url, view, false, null)
                        }
                    }
                })
        }
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            runnable.run()
        } else {
            WXSDKManager.getInstance().postOnUiThread(runnable, 0)
        }
    }
}