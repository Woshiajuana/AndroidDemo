package com.owulia.mvvmdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.mvvmdemo.R
import com.owulia.mvvmdemo.domain.OnSellData
import kotlinx.android.synthetic.main.item_on_sell.view.*

class OnSellListAdapter : RecyclerView.Adapter<OnSellListAdapter.InnerHolder>() {

    private val mContentList =
        arrayListOf<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnSellListAdapter.InnerHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_on_sell, parent, false)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    override fun onBindViewHolder(holder: OnSellListAdapter.InnerHolder, position: Int) {
        holder.itemView.apply {
            with(mContentList[position]) {
                itemTitleTv.text = title
            }
        }
    }

    fun setData(it: List<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>) {
        mContentList.clear()
        mContentList.addAll(it)
        notifyDataSetChanged()
    }
}