package com.owulia.mvvmdemo.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.mvvmdemo.domain.OnSellData

class OnSellListAdapter : RecyclerView.Adapter<OnSellListAdapter.InnerHolder>() {

    private val mContentList =
        arrayListOf<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>()

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnSellListAdapter.InnerHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: OnSellListAdapter.InnerHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun setData(it: List<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>) {
        mContentList.clear()
        mContentList.addAll(it)
        notifyDataSetChanged()
    }
}