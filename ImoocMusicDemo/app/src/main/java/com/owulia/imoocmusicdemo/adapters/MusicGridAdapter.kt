package com.owulia.imoocmusicdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.owulia.imoocmusicdemo.R

class MusicGridAdapter(context: Context) : RecyclerView.Adapter<MusicGridHolder>() {

    private val _context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicGridHolder {
        return MusicGridHolder(LayoutInflater.from(_context).inflate(R.layout.item_grid_music, parent, false))
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: MusicGridHolder, position: Int) {

    }

}

class MusicGridHolder(itemView: View) : RecyclerView.ViewHolder(itemView)