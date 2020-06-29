package com.owulia.wowcool.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.wowcool.R
import com.owulia.wowcool.bean.DemoSlideMenuItem
import com.owulia.wowcool.utils.WowLogUtils
import com.owulia.wowcool.utils.WowToastUtils
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
            vTitle.apply {
                text = itemData.title
                setOnClickListener {
                    WowToastUtils.show("主体")
                    WowLogUtils.d(this, "主体")
                }
            }
            vBtnTop.apply {
                visibility = if (position == 0) View.GONE else View.VISIBLE
                setOnClickListener {
                    WowToastUtils.show("置顶")
                    WowLogUtils.d(this, "置顶")
                }
            }
            vBtnDelete.setOnClickListener {
                WowToastUtils.show("删除")
                WowLogUtils.d(this, "删除")
            }
            vBtnUpdate.setOnClickListener {
                WowToastUtils.show("修改")
                WowLogUtils.d(this, "修改")
            }
        }
    }

    fun setData (data: List<DemoSlideMenuItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}

class DemoSlideMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
