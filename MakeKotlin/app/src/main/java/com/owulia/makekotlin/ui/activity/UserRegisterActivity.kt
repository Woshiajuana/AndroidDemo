package com.owulia.makekotlin.ui.activity

import android.content.Intent
import android.os.Handler
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseMvpActivity
import com.owulia.makekotlin.bean.WebViewOptionBean
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.presenter.UserRegisterPresenter
import com.owulia.makekotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_user_register.*

class UserRegisterActivity : BaseMvpActivity<UserRegisterPresenter>(), UserRegisterContacts.IView {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_register

    override fun bindPresenter(): UserRegisterPresenter = UserRegisterPresenter()

    private var mAccount: String? = null
    private val mDefCount = 10
    private var mCount = mDefCount

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
        initAgreement()
        initParams()
    }

    /**
     * 渲染协议部分
     * */
    private fun initAgreement() {
        val strAgreement = SpannableString(getString(R.string.string_agreement_tip))
        strAgreement.apply {
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    vAgreementButton.isChecked = !vAgreementButton.isChecked
                }
            }, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(object : UnderlineSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ContextCompat.getColor(this@UserRegisterActivity, R.color.colorText9)
                    ds.isUnderlineText = false
                }
            }, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@UserRegisterActivity, WebViewActivity::class.java)
                    intent.putExtra(
                        Constants.KEY_WEB_VIEW_OPTION_MODEL,
                        WebViewOptionBean(
                            link = getString(R.string.string_link_agreement_service),
                            title = getString(R.string.string_service_agreement)
                        )
                    )
                    startActivity(intent)
                }
            }, 7, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(object : UnderlineSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ContextCompat.getColor(this@UserRegisterActivity, R.color.colorMain)
                    ds.isUnderlineText = false
                }
            }, 7, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@UserRegisterActivity, WebViewActivity::class.java)
                    intent.putExtra(
                        Constants.KEY_WEB_VIEW_OPTION_MODEL,
                        WebViewOptionBean(
                            link = getString(R.string.string_link_agreement_privacy),
                            title = getString(R.string.string_privacy_agreement)
                        )
                    )
                    startActivity(intent)
                }
            }, 14, strAgreement.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(object : UnderlineSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ContextCompat.getColor(this@UserRegisterActivity, R.color.colorMain)
                    ds.isUnderlineText = false
                }
            }, 14, strAgreement.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        vAgreement.apply {
            text = strAgreement
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor =
                ContextCompat.getColor(this@UserRegisterActivity, android.R.color.transparent)
        }
    }

    /**
     * 参数获取
     * */
    private fun initParams() {
        intent.apply {
            getStringExtra(Constants.KEY_ACCOUNT)?.let {
                mAccount = it
            }
        }
    }

    override fun initListener() {
        super.initListener()
        vCodeButton.setOnClickListener {
            val params = HashMap<String, Any?> ()
            params["loginNo"] = mAccount?: ""
            params["smsType"] = "COMMON"
            mvpPresenter?.doSendSms(params)
        }
        vTypeSwitch.setOnClickListener {
            vInputPassword.inputType =
                if (vInputPassword.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    InputType.TYPE_CLASS_TEXT
                else InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    private fun codeButtonStatus () {
        val isClickable = mCount == mDefCount
        vCodeButton.apply {
            this.isClickable = isClickable
            if (isClickable) {
                text = getString(R.string.string_code_btn)
            } else {
                text = "$mCount s"
            }
        }
    }

    fun countDown () {
        mCount--
        if (mCount <= 0) {
            mCount = mDefCount
            return codeButtonStatus()
        }
        Handler().postDelayed({
            countDown()
        }, 1000)
    }

    override fun callbackRegisterSuccess() {
        TODO("Not yet implemented")
    }

    override fun callbackSendSms() {
        TODO("Not yet implemented")
    }

}
