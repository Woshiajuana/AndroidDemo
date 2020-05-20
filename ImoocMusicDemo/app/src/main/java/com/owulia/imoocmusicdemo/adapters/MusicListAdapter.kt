package com.owulia.imoocmusicdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.owulia.imoocmusicdemo.R
import kotlinx.android.synthetic.main.item_list_music.view.*

class MusicListAdapter (context: Context, parent: RecyclerView?) : RecyclerView.Adapter<MusicListHolder>() {
    private val _context = context
    private val _parent = parent
    private lateinit var _itemView : View
    private var _isSetHeight = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicListHolder {
        _itemView = LayoutInflater.from(_context).inflate(R.layout.item_list_music, parent, false)
        return MusicListHolder(_itemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MusicListHolder, position: Int) {
        setRecyclerViewHeight()
        Glide.with(holder.itemView)
            .load("https://img4.mukewang.com/szimg/5d43953c09c0247612000676-228-128.png")
            .into(holder.itemView.mItemIcon)
    }

    private fun setRecyclerViewHeight () {
        if (_isSetHeight || _parent == null) return
        _isSetHeight = true
        val itemViewHeight = _itemView.layoutParams.height
        val recycleViewHeight = itemCount * itemViewHeight
        _parent.layoutParams.height = recycleViewHeight
    }
}

class MusicListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)