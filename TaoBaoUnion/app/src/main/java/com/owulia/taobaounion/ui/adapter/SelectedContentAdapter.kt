package com.owulia.taobaounion.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.taobaounion.R
import com.owulia.taobaounion.model.domain.SelectedPageContent

class SelectedContentAdapter : RecyclerView.Adapter<SelectedContentViewHolder> (){

    private var data = ArrayList<SelectedPageContent.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_selected_content, parent, false)
        return SelectedContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SelectedContentViewHolder, position: Int) {
    }

    fun setData (contents: SelectedPageContent.Data) {
        data.clear()
        data.addAll(contents)
        notifyDataSetChanged()
    }

}

class SelectedContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)