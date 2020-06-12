package com.owulia.wowcool.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.owulia.wowcool.R
import com.owulia.wowcool.bean.DemoItemBean
import kotlinx.android.synthetic.main.demo_card_cell.view.*

class DemoAdapter (private val list: MutableList<DemoItemBean>) : RecyclerView.Adapter<DemoAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.demo_card_cell, parent, false)
        val holder = DemoAdapterHolder(view)
        Log.d("DEMOADAPTER", "111111111${holder.itemView}")

        holder.itemView.llContent.setOnClickListener{
            Log.d("DEMOADAPTER", "111111111")
            holder.itemView.findNavController().navigate(R.id.action_demoFragment_to_demoSlideMenuFragment)
        }
        return holder
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
    }

}

class DemoAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView)