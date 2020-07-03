package com.owulia.makekotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.apache.weex.IWXRenderListener
import org.apache.weex.WXSDKInstance
import org.apache.weex.common.WXRenderStrategy

class MainActivity : AppCompatActivity(), IWXRenderListener {
    var mWXSDKInstance: WXSDKInstance? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)
        mWXSDKInstance = WXSDKInstance(this)
        mWXSDKInstance!!.registerRenderListener(this)
        val pageName = "WXSample"
//        String bundleUrl = "http://dotwe.org/raw/dist/38e202c16bdfefbdb88a8754f975454c.bundle.wx1";
        //        String bundleUrl = "http://dotwe.org/raw/dist/38e202c16bdfefbdb88a8754f975454c.bundle.wx1";
        val bundleUrl =
            "https://ossmk2.jfpays.com/www_make_v1/app/0.0.36/mk_about.js"
        mWXSDKInstance!!.renderByUrl(pageName, bundleUrl, null, null, WXRenderStrategy.APPEND_ASYNC)


    }

    override fun onRefreshSuccess(instance: WXSDKInstance?, width: Int, height: Int) {
    }

    override fun onException(instance: WXSDKInstance?, errCode: String?, msg: String?) {
    }

    override fun onViewCreated(instance: WXSDKInstance?, view: View?) {
        setContentView(view)
    }

    override fun onRenderSuccess(instance: WXSDKInstance?, width: Int, height: Int) {
    }

    override fun onResume() {
        super.onResume()
        if (mWXSDKInstance != null) {
            mWXSDKInstance?.onActivityResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (mWXSDKInstance != null) {
            mWXSDKInstance?.onActivityPause()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mWXSDKInstance != null) {
            mWXSDKInstance?.onActivityStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mWXSDKInstance != null) {
            mWXSDKInstance?.onActivityDestroy()
        }
    }

}
