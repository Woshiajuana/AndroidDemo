package com.owulia.wowcool.ui.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class WowIconFontView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val iconFontTypeFace: Typeface by lazy {
        Typeface.createFromAsset(context.assets, "fonts/iconfont.ttf")
    }

    init {
        typeface = iconFontTypeFace
        includeFontPadding = false
    }

}

