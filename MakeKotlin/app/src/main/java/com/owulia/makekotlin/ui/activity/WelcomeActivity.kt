package com.owulia.makekotlin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.owulia.makekotlin.R
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowJsonCacheUtils
import com.owulia.makekotlin.widget.WowConfirmDialog

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 判断用户是否第一次打开 APP
         * */
        val isFirstOpen = WowJsonCacheUtils.getInstance().get(Constants.JSON_CACHE_KEY_FIRST_OPEN, Boolean::class.java, true) as Boolean

        if (isFirstOpen) {
            /**
             * 如果是第一次 弹窗协议
             * */
            WowConfirmDialog(this).apply {
                setCanceledOnTouchOutside(false)
                show()
            }
        }



//        Handler().postDelayed({
//            val intent = Intent(this, GuidePageActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
//            finish()

//            WowConfirmDialog(this).apply {
//                setCanceledOnTouchOutside(false)
//                show()
//            }
//        }, 1200)
    }
}
