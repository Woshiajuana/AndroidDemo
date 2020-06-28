package com.owulia.wowcool.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.wowcool.R
import com.owulia.wowcool.bean.DemoSlideMenuItem
import kotlinx.android.synthetic.main.item_demo_slide_menu_cell.view.*

class DemoSlideMenuAdapter : RecyclerView.Adapter<DemoSlideMenuViewHolder>() {

    private val list = mutableListOf<DemoSlideMenuItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoSlideMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_demo_slide_menu_cell, parent, false)
        return DemoSlideMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DemoSlideMenuViewHolder, position: Int) {
        val itemData = list[position]
        holder.itemView.apply {
            vTitle.text = itemData.title
            vBtnTop.visibility = if (position == 0) View.GONE else View.VISIBLE
        }
    }

    fun setData (data: List<DemoSlideMenuItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}

class DemoSlideMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
