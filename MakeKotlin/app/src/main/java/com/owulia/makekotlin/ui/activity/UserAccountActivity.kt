package com.owulia.makekotlin.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.makekotlin.R
import com.owulia.makekotlin.adapter.UserHistoryAccountAdapter
import com.owulia.makekotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_user_account.*

class UserAccountActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_account

    override val isUseNavBar: Boolean = false

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)

        vHistoryAccountBox.apply {
            adapter = UserHistoryAccountAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }
}
