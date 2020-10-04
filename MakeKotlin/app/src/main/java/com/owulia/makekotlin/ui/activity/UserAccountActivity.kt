package com.owulia.makekotlin.ui.activity

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.makekotlin.R
import com.owulia.makekotlin.adapter.UserHistoryAccountAdapter
import com.owulia.makekotlin.base.BaseMvpActivity
import com.owulia.makekotlin.contacts.UserAccountContacts
import com.owulia.makekotlin.presenter.UserAccountPresenter
import com.owulia.makekotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_user_account.*
import kotlinx.android.synthetic.main.widget_button.*
import kotlinx.android.synthetic.main.widget_input_clear_btn.*

class UserAccountActivity : BaseMvpActivity<UserAccountPresenter>(), UserAccountContacts.IView {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_account

    override val isUseNavBar: Boolean = false

    override fun bindPresenter(): UserAccountPresenter = UserAccountPresenter()

    private var mUserHistoryAccountAdapter: UserHistoryAccountAdapter? = null

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)

        vHistoryAccountBox.apply {
            mUserHistoryAccountAdapter = UserHistoryAccountAdapter()
            adapter = mUserHistoryAccountAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun initListener() {
        super.initListener()

        // 触发
        mUserHistoryAccountAdapter?.apply {
            setDeleteOnClickListener = {
                mvpPresenter?.delHistoryAccount(it)
                true
            }
            // 触发赋值历史数据
            setItemOnClickListener = {
                vAccountInput.setText(it)
                vHistoryOpen.performClick()
            }
        }

        // 登录
        vSubmitButton.setOnClickListener{
            mvpPresenter?.checkAccount(vAccountInput.text.toString())
        }

        // input 输入监听
        vAccountInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(s.toString())) {
                    vInputClear.visibility = View.GONE
                } else {
                    vInputClear.visibility = View.VISIBLE
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // 清空 input 内容
        vInputClear.setOnClickListener {
            vAccountInput.setText("")
        }

        // 打开关闭登录历史
        vHistoryOpen.setOnClickListener { it as ImageView
            vHistoryAccountBox.apply {
                if (visibility == View.VISIBLE) {
                    // 关闭
                    visibility = View.GONE
                    it.setImageResource(R.drawable.ic_arrow_down_999)
                } else {
                    // 打开
                    mvpPresenter?.getHistoryAccount()
                    visibility = View.VISIBLE
                    it.setImageResource(R.drawable.ic_arrow_up_999)
                }
            }
        }

    }

    override fun callbackGoToLogin(account: String) {
        val intent = Intent(this, UserLoginActivity::class.java)
        intent.putExtra(Constants.KEY_ACCOUNT, account)
        startActivity(intent)
    }

    override fun callbackGoToRegister(account: String) {
        val intent = Intent(this, UserRegisterActivity::class.java)
        intent.putExtra(Constants.KEY_ACCOUNT, account)
        startActivity(intent)
    }

    override fun callbackHistoryAccount(data: ArrayList<String>) {
        mUserHistoryAccountAdapter?.setData(data)
    }

}
