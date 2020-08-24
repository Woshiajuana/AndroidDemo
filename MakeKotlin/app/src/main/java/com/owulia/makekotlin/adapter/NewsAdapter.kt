package com.owulia.makekotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.makekotlin.R

class NewsAdapter: RecyclerView.Adapter<NewsAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {
    }

}

class NewsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)