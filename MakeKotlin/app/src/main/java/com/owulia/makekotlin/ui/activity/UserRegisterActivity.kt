package com.owulia.makekotlin.ui.activity

import android.content.Intent
import android.os.Handler
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseMvpActivity
import com.owulia.makekotlin.bean.WebViewOptionBean
import com.owulia.makekotlin.contacts.UserRegisterContacts
import com.owulia.makekotlin.presenter.UserRegisterPresenter
import com.owulia.makekotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_user_register.*
import kotlinx.android.synthetic.main.widget_button.*

class UserRegisterActivity : BaseMvpActivity<UserRegisterPresenter>(), UserRegisterContacts.IView,
    TextWatcher {

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
            mvpPresenter?.doSendSms(mAccount ?: "")
        }

        vTypeSwitch.setOnClickListener {
            it as ImageView
            vInputPassword.apply {
                inputType = if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    /**隐藏*/
                    it.setImageResource(R.mipmap.ic_eye_off)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                } else {
                    /**显示*/
                    it.setImageResource(R.mipmap.ic_eye_on)
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                /**设置光标*/
                setSelection(length())
            }
        }

        vInputPassword.addTextChangedListener(this)

        vInputCode.addTextChangedListener(this)

        vSubmitButton.setOnClickListener {
            if (vAgreementButton.isChecked) {
                mvpPresenter?.doUserRegister(
                    mAccount ?: "",
                    vInputPassword.text.toString(),
                    vInputCode.text.toString()
                )
            } else {
                toast(R.string.string_agreement_toast)
            }
        }
    }


    private fun codeButtonStatus() {
        val isBoolean = mCount == mDefCount
        vCodeButton.apply {
            isEnabled = isBoolean
            text = if (isBoolean) {
                getString(R.string.string_code_btn)
            } else {
                "$mCount s"
            }
        }
    }

    private fun countDown() {
        mCount--
        if (mCount <= 0) {
            mCount = mDefCount
        } else {
            Handler().postDelayed({
                countDown()
            }, 1000)
        }
        codeButtonStatus()
    }

    override fun callbackRegisterSuccess() {
        toast(R.string.string_register_success_tip)
        finish()
    }

    override fun callbackSendSms() {
        countDown()
        toast(R.string.string_sms_send_success_tip)
    }

    override fun afterTextChanged(s: Editable?) {
        vSubmitButton.isEnabled =
            !TextUtils.isEmpty(vInputCode.text) && !TextUtils.isEmpty(vInputPassword.text)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}
