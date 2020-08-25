package com.owulia.makekotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.item_mine_menu.view.*

class MenuCellView @JvmOverloads constructor(
    context: Context,
    labelText: Int?,
    valueText: Int?,
    prefixImg: Int?,
    suffixImg: Int? = R.mipmap.ic_arrow_grey_right,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val setOnClickListener: ((View) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.item_mine_menu, this, true)

        if (prefixImg !== null) {
            vPrefixImg.setImageResource(prefixImg)
        } else {
            vPrefixImg.visibility = View.GONE
        }

        if (suffixImg !== null) {
            vSuffixImg.setImageResource(suffixImg)
        } else {
            vSuffixImg.visibility = View.GONE
        }

        if (labelText !== null) {
            vLabelText.setText(labelText)
        }

        if (valueText !== null) {
            vValueText.setText(valueText)
        }

        this.setOnClickListener { v ->
            setOnClickListener?.let { it(v) }
        }

    }

}