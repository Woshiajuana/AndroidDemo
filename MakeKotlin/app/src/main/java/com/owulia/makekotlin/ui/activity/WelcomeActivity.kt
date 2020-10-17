package com.owulia.makekotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.bean.RespUserInfoBean
import com.owulia.makekotlin.bean.UserBean
import com.owulia.makekotlin.bean.WebViewOptionBean
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowJsonCacheUtils
import com.owulia.makekotlin.widget.WowConfirmDialog
import kotlinx.android.synthetic.main.widget_confirm_dialog.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 判断用户是否登录
         * */
        val isUserLogin = WowJsonCacheUtils.getInstance().get(Constants.JSON_CACHE_KEY_USER, RespUserInfoBean::class.java, null) != null

        if (isUserLogin) {

            /**
             * 用户已登录，进入到首页
             * */
            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } else {

            /**
             * 用户未登录
             *
             * 首先判断用户是否第一次打开 APP
             * */
            val isFirstOpen = WowJsonCacheUtils.getInstance().get(Constants.JSON_CACHE_KEY_FIRST_OPEN, Boolean::class.java, true) as Boolean

            if (!isFirstOpen) {

                /**
                 * 如果是第一次 弹窗协议
                 * */
                renderAgreementPopup()

            } else {

                /**
                 * 不是第一次，则进登录页面
                 * */
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
                startActivity(Intent(this, UserAccountActivity::class.java))
                finish()
            }
        }
    }

    /**
     * 弹窗协议
     * */
    private fun renderAgreementPopup() {
        WowConfirmDialog(this).apply {
            setCancelable(false) // 禁止物理键返回
            setCanceledOnTouchOutside(false) // 禁止点击黑色蒙层
            show()
            vTitle.apply {
                setText(R.string.string_agreement_title)
            }
            vCancelButton.apply {
                setText(R.string.string_agreement_cancel)
            }
            vSureButton.apply {
                setText(R.string.string_agreement_sure)
            }
            vMessage.apply {
                val str1 = SpannableString(getString(R.string.string_agreement_popup_str1))
                val str2 = SpannableString(getString(R.string.string_service_agreement))
                str2.apply {
                    setSpan(object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            val intent = Intent(this@WelcomeActivity, WebViewActivity::class.java)
                            intent.putExtra(Constants.KEY_WEB_VIEW_OPTION_MODEL,
                                WebViewOptionBean(
                                    link = getString(R.string.string_link_agreement_service),
                                    title = getString(R.string.string_service_agreement)
                                )
                            )
                            startActivity(intent)
                        }
                    }, 0, str2.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(object : UnderlineSpan() {
                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.color = ContextCompat.getColor(context, R.color.colorMain)
                            ds.isUnderlineText = false
                        }
                    }, 0, str2.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                val str3 = SpannableString(getString(R.string.string_agreement_popup_str2))
                val str4 = SpannableString(getString(R.string.string_privacy_agreement))
                str4.apply {
                    setSpan(object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            val intent = Intent(this@WelcomeActivity, WebViewActivity::class.java)
                            intent.putExtra(Constants.KEY_WEB_VIEW_OPTION_MODEL,
                                WebViewOptionBean(
                                    link = getString(R.string.string_link_agreement_privacy),
                                    title = getString(R.string.string_privacy_agreement)
                                )
                            )
                            startActivity(intent)
                        }
                    }, 0, str4.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(object : UnderlineSpan() {
                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.color = ContextCompat.getColor(context, R.color.colorMain)
                            ds.isUnderlineText = false
                        }
                    }, 0, str4.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                val str5 =
                    SpannableString(getString(R.string.string_agreement_popup_str3))
                val message = SpannableStringBuilder()
                message.append(str1)
                message.append(str2)
                message.append(str3)
                message.append(str4)
                message.append(str5)
                text = message
                movementMethod = LinkMovementMethod.getInstance()
                highlightColor = ContextCompat.getColor(this@WelcomeActivity, android.R.color.transparent)
            }
            setCancelOnClickListener = {
                /**
                 * 点击了取消 直接退出 app
                 * */
                finish()
                false
            }
            setSureOnClickListener = {
                /**
                 * 点击了同意 进入到引导页面
                 * */
                hide()
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
                startActivity(Intent(this@WelcomeActivity, GuidePageActivity::class.java))
                finish()
                false
            }
        }
    }
}
