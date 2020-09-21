package com.owulia.makekotlin.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.owulia.makekotlin.R
import com.owulia.makekotlin.model.MenuCellModel
import kotlinx.android.synthetic.main.item_mine_menu.view.*

class MenuCellView @JvmOverloads constructor(
    context: Context,
    menuCellModel: MenuCellModel? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_mine_menu, this, true)
        menuCellModel?.apply {
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
            this@MenuCellView.setOnClickListener { v ->
                setOnClickListener?.let { it(v) }
            }
        }
    }

}