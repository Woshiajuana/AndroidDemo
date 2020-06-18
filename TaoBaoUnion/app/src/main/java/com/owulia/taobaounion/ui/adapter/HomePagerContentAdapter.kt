package com.owulia.taobaounion.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.owulia.taobaounion.R
import com.owulia.taobaounion.model.domain.HomePagerContent
import com.owulia.taobaounion.utils.UrlUtil
import kotlinx.android.synthetic.main.item_home_pager_content.view.*

class HomePagerContentAdapter : RecyclerView.Adapter<HomePagerContentHolder>() {

    private val data = ArrayList<HomePagerContent.Data>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePagerContentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_pager_content, parent, false)
        return HomePagerContentHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HomePagerContentHolder, position: Int) {
        val itemData = data[position]
        holder.setData(itemData)
    }

    fun setData(contents: List<HomePagerContent.Data>) {
        data.clear()
        data.addAll(contents)
        notifyDataSetChanged()
    }

    fun addData(contents: List<HomePagerContent.Data>) {
        val start = data.size
        data.addAll(contents)
        notifyItemRangeChanged(start, contents.size)
    }
}

class HomePagerContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(itemData: HomePagerContent.Data) {
        itemView.apply {
            goodsTitle.text = itemData.title
            goodsOffPrise.text = String.format(context.getString(R.string.text_goods_off_prise), itemData.coupon_amount)
            goodsAfterPrice.text = String.format("%.2f", (itemData.zk_final_price.toFloat()) - itemData.coupon_amount)
            goodsOriginalPrice.text =String.format(context.getString(R.string.text_goods_original_price), itemData.zk_final_price)
            goodsOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            goodsNumber.text = String.format(context.getString(R.string.text_goods_number), itemData.volume)
        }
        val url = UrlUtil.getCoverPath(itemData.pict_url)
        Glide.with(itemView)
            .load(url)
            .into(itemView.goodsCover)
    }
}