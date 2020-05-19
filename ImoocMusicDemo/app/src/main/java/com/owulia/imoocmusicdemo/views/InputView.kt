package com.owulia.imoocmusicdemo.views

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.owulia.imoocmusicdemo.R
import kotlinx.android.synthetic.main.input_view.view.*
import java.util.*

// 自定义 View
// input_icon
class InputView  : FrameLayout {

    constructor(ctx: Context) : super(ctx) {
        init(ctx, null)
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init(ctx, attrs)
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun init (ctx: Context, attrs: AttributeSet?) {
        if (attrs == null) return
        // 获取自定义属性
        val typedArray =  context.obtainStyledAttributes(attrs, R.styleable.inputView)
        var inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.drawable.ic_arrow)
        var inputHint = typedArray.getString(R.styleable.inputView_input_hint).toString()
        var isPassword = typedArray.getBoolean(R.styleable.inputView_is_password, false)
        typedArray.recycle()

        val mView = LayoutInflater.from(ctx).inflate(R.layout.input_view, this, false)
        mInputIcon.setImageResource(inputIcon)
        mInputEdit.hint = inputHint
        mInputEdit.inputType = if (isPassword) InputType.TYPE_NUMBER_VARIATION_PASSWORD else InputType.TYPE_CLASS_PHONE

//        set(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_NUMBER_VARIATION_PASSWORD )

        addView(mView)
    }

    public fun getInputStr () : String {
        return mInputEdit.text.toString().trim()
    }
}