package com.owulia.mall.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.base.ui.adapter.BaseRecyclerViewAdapter
import com.owulia.base.utils.GlideUtils
import com.owulia.mall.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

class HomeDiscountAdapter(context: Context) : BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>(context){

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.mDiscountBeforeTv.apply {
                paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                paint.isAntiAlias = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        GlideUtils.loadImage(mContext, dataList[position], holder.itemView.mGoodsIconIv)
        holder.itemView.apply {
            mDiscountAfterTv.text = "￥123.00"
            mDiscountBeforeTv.text = "$1000.00"
        }

    }

}