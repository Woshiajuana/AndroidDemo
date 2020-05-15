package com.owulia.makekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owulia.makekotlin.views.HomeFragment

class MainActivity : AppCompatActivity() {

    private var numCurrent = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction().add(R.id.frameLayout, HomeFragment()).commit()
    }

    private fun init () {

    }

}
