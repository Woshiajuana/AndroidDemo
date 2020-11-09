package com.owulia.mall.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owulia.mall.R
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(20)

        Observable.timer(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(){
                mBottomNavBar.checkMsgBadge(true)
            }
    }
}
