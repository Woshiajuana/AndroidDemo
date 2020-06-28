package com.owulia.wowcool.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.owulia.wowcool.R
import com.owulia.wowcool.bean.DemoItemBean
import kotlinx.android.synthetic.main.item_demo_card_cell.view.*

class DemoAdapter (private val list: MutableList<DemoItemBean>) : RecyclerView.Adapter<DemoAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_demo_card_cell, parent, false)
        return DemoAdapterHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DemoAdapterHolder, position: Int) {
        val demoItemBean = list[position]
        holder.itemView.tvIcon.text = demoItemBean.icon
        holder.itemView.tvText.text = demoItemBean.text
        if ((position + 1) % 3 == 0) {
            holder.itemView.brRight.visibility = View.GONE
        }
        demoItemBean.route?.let {
            holder.itemView.setOnClickListener{ _ ->
                
                holder.itemView.findNavController().navigate(it)
            }
        }
    }

}

class DemoAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView)