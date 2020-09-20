package com.owulia.makekotlin.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.owulia.makekotlin.R
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowJsonCacheUtils
import com.owulia.makekotlin.widget.WowConfirmDialog
import kotlinx.android.synthetic.main.widget_confirm_dialog.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 判断用户是否第一次打开 APP
         * */
        val isFirstOpen = WowJsonCacheUtils.getInstance().get(Constants.JSON_CACHE_KEY_FIRST_OPEN, Boolean::class.java, true) as Boolean

        if (isFirstOpen) {
            /**
             * 如果是第一次 弹窗协议
             * */
            WowConfirmDialog(this).apply {
                setCancelable(false) // 禁止物理键返回
                setCanceledOnTouchOutside(false) // 禁止点击黑色蒙层
                getTitleView().apply {
                    text = context.getString(R.string.string_agreement_title)
                }
                getCancelButtonView().apply {
                    text = context.getString(R.string.string_agreement_cancel)
                }
                getSureButtonView().apply { 
                    text = context.getString(R.string.string_agreement_sure)
                }
                getMessageView().apply {
                    val str1 = SpannableString("我们非常注重您的个人信息和隐私保护，为了更好的保证您的个人权益，请您认证阅读")
                    val str2 = SpannableString("《用户协议》").apply {
                        setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorMain)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        setSpan({
                            
                        }, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                    val str3 = SpannableString("和")
                    val str4 = SpannableString("《隐私政策》")
                    val str5 = SpannableString("的全部内容，同意并接收全部条款后开始使用我们的产品和服务。")
                    val str6 = SpannableString("点击同意视为接受全部条款，开始体验码可的产品和服务吧")
                    val message = SpannableStringBuilder();
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
                     * 点击了同意
                     * */
                    false
                }
                show()
            }
        }



//        Handler().postDelayed({
//            val intent = Intent(this, GuidePageActivity::class.java)
//            startActivity(intent)
//            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
//            finish()

//            WowConfirmDialog(this).apply {
//                setCanceledOnTouchOutside(false)
//                show()
//            }
//        }, 1200)
    }
}
