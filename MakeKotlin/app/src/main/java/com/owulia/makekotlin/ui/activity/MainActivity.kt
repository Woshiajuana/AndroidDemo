package com.owulia.makekotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    override val mNavBarTitle: Int = R.string.text_error_tip
//
//    override fun getContentViewResourceId(): Int = R.layout.activity_main
//
//    override fun initView() {
//        super.initView()
//        render(RenderState.SUCCESS)
//    }
}
