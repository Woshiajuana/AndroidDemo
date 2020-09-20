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
import com.owulia.makekotlin.model.UserModel
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowJsonCacheUtils
import com.owulia.makekotlin.widget.WowConfirmDialog
import com.owulia.makekotlin.widget.WowToastUtils

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 判断用户是否登录
         * */
        val isUserLogin = WowJsonCacheUtils.getInstance().get(Constants.JSON_CACHE_KEY_USER, UserModel::class.java, null) != null

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

            if (isFirstOpen) {

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
            getTitleView().apply {
                setText(R.string.string_agreement_title)
            }
            getCancelButtonView().apply {
                setText(R.string.string_agreement_cancel)
            }
            getSureButtonView().apply {
                setText(R.string.string_agreement_sure)
            }
            getMessageView().apply {
                val str1 = SpannableString("我们非常注重您的个人信息和隐私保护，为了更好的保证您的个人权益，请您认证阅读")
                val str2 = SpannableString("《用户协议》").apply {
                    setSpan(object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            WowToastUtils.show("点了用户协议")
                        }
                    }, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(object : UnderlineSpan() {
                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.color = ContextCompat.getColor(context, R.color.colorMain)
                            ds.isUnderlineText = false
                        }
                    }, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    movementMethod = LinkMovementMethod.getInstance()
                }
                val str3 = SpannableString("和")
                val str4 = SpannableString("《隐私政策》").apply {
                    setSpan(object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            WowToastUtils.show("点了隐私政策")
                        }
                    }, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    setSpan(object : UnderlineSpan() {
                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.color = ContextCompat.getColor(context, R.color.colorMain)
                            ds.isUnderlineText = false
                        }
                    }, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    movementMethod = LinkMovementMethod.getInstance()
                }
                val str5 =
                    SpannableString("的全部内容，同意并接收全部条款后开始使用我们的产品和服务。\n点击同意视为接受全部条款，开始体验码可的产品和服务吧")
                val message = SpannableStringBuilder()
                message.append(str1)
                message.append(str2)
                message.append(str3)
                message.append(str4)
                message.append(str5)
                text = message
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
