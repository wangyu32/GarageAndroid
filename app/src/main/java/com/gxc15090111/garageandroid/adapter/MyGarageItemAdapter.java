package com.gxc15090111.garageandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.gxc15090111.garageandroid.activitys.R;
import com.gxc15090111.garageandroid.entity.GarageItem;
import com.gxc15090111.garageandroid.enums.GarageItemStatusEnum;

/**
 * 车位分布
 */
public class MyGarageItemAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<GarageItem> garageItemList;

    public MyGarageItemAdapter() {
    }

    public MyGarageItemAdapter(Context mContext, List<GarageItem> garageItemList) {
        this.mContext = mContext;
        this.garageItemList = garageItemList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return garageItemList.size() / 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
//        public TextView tvIndex, tvIntime, tvOuttime, tvTotal, tvAmount;
        public TextView tvCode1;
        public TextView tvCode2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.garage_item, null);
            holder = new ViewHolder();
            holder.tvCode1 = convertView.findViewById(R.id.tv_gi_code_1);
            holder.tvCode2 = convertView.findViewById(R.id.tv_gi_code_2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GarageItem item1 = this.garageItemList.get(position*2);
        GarageItem item2 = this.garageItemList.get(position*2+1);

        holder.tvCode1.setText(item1.getCode());
        holder.tvCode2.setText(item2.getCode());

        if(item1.getStatus() == GarageItemStatusEnum.NO_CAR.value()){
            holder.tvCode1.setBackgroundColor(Color.parseColor("#00FF00"));
//            convertView.setBackgroundColor(Color.parseColor("#00FF00"));
        } else {
            holder.tvCode1.setBackgroundColor(Color.parseColor("#FF0000"));
//            convertView.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        if(item2.getStatus() == GarageItemStatusEnum.NO_CAR.value()){
            holder.tvCode2.setBackgroundColor(Color.parseColor("#00FF00"));
//            convertView.setBackgroundColor(Color.parseColor("#00FF00"));
        } else {
            holder.tvCode2.setBackgroundColor(Color.parseColor("#FF0000"));
//            convertView.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        return convertView;
    }

}
