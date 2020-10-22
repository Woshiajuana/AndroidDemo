package com.owulia.testprovider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*
import java.util.ArrayList

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.InnerHolder>() {

    private val mData = arrayListOf<ImageItem>()

    class InnerHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        val point = SizeUtils.getScreenSize(itemView.context)
        itemView.layoutParams = RecyclerView.LayoutParams(point.x / 3, point.x / 3)
        return InnerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        val imageItem = mData[position]
        holder.itemView.apply {
            Glide.with(context).load(imageItem.path).into(vImage)
        }
    }

    fun setData(mImageList: ArrayList<ImageItem>) {
        mData.clear()
        mData.addAll(mImageList)
        notifyDataSetChanged()
    }
}