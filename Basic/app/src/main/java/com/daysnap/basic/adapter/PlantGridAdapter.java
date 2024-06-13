package com.daysnap.basic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daysnap.basic.R;
import com.daysnap.basic.bean.Planet;

import java.util.List;

public class PlantGridAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Planet> mPlantList;


    public PlantGridAdapter(Context mContext, List<Planet> mPlantList) {
        this.mContext = mContext;
        this.mPlantList = mPlantList;
    }

    @Override
    public int getCount() {
        return mPlantList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid, null);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvDesc = convertView.findViewById(R.id.tv_desc);
            holder.ivImage = convertView.findViewById(R.id.iv_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Planet planet = mPlantList.get(position);
        holder.tvName.setText(planet.name);
        holder.ivImage.setImageResource(planet.image);
        holder.tvDesc.setText(planet.desc);
        return convertView;
    }

    public static final class ViewHolder {
        public TextView tvName;
        public TextView tvDesc;
        public ImageView ivImage;
    }
}
