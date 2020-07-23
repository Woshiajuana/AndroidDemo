package com.owulia.heimaplayer.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.owulia.heimaplayer.R
import com.owulia.heimaplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initData() {
        super.initData()
        ViewCompat.animate(imageView).apply {
            scaleX(1.0f)
            scaleY(1.0f)
            duration = 2000
            setListener(this@SplashActivity)
        }
    }

    override fun onAnimationEnd(view: View?) {
        // 动画结束
        startActivity<MainActivity>()
        finish()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

}
