package com.owulia.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owulia.user.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.setOnClickListener {
            toast("xxx")
        }
    }
}
