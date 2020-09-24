package com.owulia.makekotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.makekotlin.R
import com.owulia.makekotlin.bean.MaterialMenuBean
import kotlinx.android.synthetic.main.item_material_menu.view.*

class MaterialMenuAdapter(
    private val mArrMenu: Array<MaterialMenuBean>
) : RecyclerView.Adapter<MaterialMenuAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MaterialMenuAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_material_menu, parent, false)
        return MaterialMenuAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mArrMenu.size
    }

    override fun onBindViewHolder(holder: MaterialMenuAdapterViewHolder, position: Int) {
        val materialMenuModel = mArrMenu[position]
        holder.itemView.apply {
            materialMenuModel.text?.let { vText.setText(it) }
            materialMenuModel.icon?.let { vIcon.setImageResource(it) }
            materialMenuModel.setOnClickListener?.let {
                setOnClickListener { it(this) }
            }
        }
    }

}

class MaterialMenuAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
