package com.owulia.makekotlin.ui.activity

import android.content.Intent
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.model.WebViewOptionModel
import com.owulia.makekotlin.utils.Constants
import kotlinx.android.synthetic.main.activity_user_register.*

class UserRegisterActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_register

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
        initAgreement()
    }

    override fun initListener() {
        super.initListener()
        /**
         * 协议同意 或 取消
         * */
        vAgreementRadioButton.setOnClickListener {
            it.isClickable = !it.isClickable
        }
    }

    /**
     * 渲染协议部分
     * */
    private fun initAgreement () {
        val strAgreement = SpannableString(getString(R.string.string_agreement_tip))
        strAgreement.apply {
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    vAgreementRadioButton.isChecked = !vAgreementRadioButton.isChecked
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
                        Constants.KEY_WEB_VIEW_OPTION_MODEL, WebViewOptionModel(
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
                    intent.putExtra(Constants.KEY_WEB_VIEW_OPTION_MODEL, WebViewOptionModel(
                        link = getString(R.string.string_link_agreement_privacy),
                        title = getString(R.string.string_privacy_agreement)
                    ))
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
            highlightColor = ContextCompat.getColor(this@UserRegisterActivity, android.R.color.transparent)
        }
    }

}
