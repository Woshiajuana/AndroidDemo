package com.owulia.taobaounion.ui.adapter

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
}

class HomePagerContentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(itemData: HomePagerContent.Data) {
        itemView.goodsTitle.text = itemData.title
        val url = UrlUtil.getCoverPath(itemData.pict_url)
        Glide.with(itemView)
            .load(url)
            .into(itemView.goodsCover)
    }
}