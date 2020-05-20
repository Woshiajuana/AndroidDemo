package com.owulia.imoocmusicdemo.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.owulia.imoocmusicdemo.R
import java.util.*

// 延迟3秒
// 跳转页面
class WelcomeActivity : BaseActivity() {

    private lateinit var mTimer : Timer
    private val TAG = "WelcomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        init()
    }

    // 初始化
    private fun init () {
        mTimer = Timer()
        mTimer.schedule(object: TimerTask() {
            override fun run() {
                Log.d(TAG, "当前线程${Thread.currentThread()}")
                toMain()
//                toLogin()
            }
        }, 3 * 10)
    }

    // 跳转页面
    private fun toMain () {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toLogin () {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
