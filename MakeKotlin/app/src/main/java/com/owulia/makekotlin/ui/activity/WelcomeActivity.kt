package com.owulia.makekotlin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.owulia.makekotlin.R
import com.owulia.makekotlin.widget.WowConfirmDialog

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 判断用户是否第一次进入 APP
         *
         * 如果是第一次进入
         *
         * */

        /**
         * 如果是第一次
         * */

        Handler().postDelayed({
//            val intent = Intent(this, GuidePageActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
//            finish()

            WowConfirmDialog(this).apply {
                setCanceledOnTouchOutside(false)
                show()
            }
        }, 1200)
    }
}
