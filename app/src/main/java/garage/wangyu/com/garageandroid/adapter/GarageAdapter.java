package garage.wangyu.com.garageandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import garage.wangyu.com.garageandroid.activitys.R;
import garage.wangyu.com.garageandroid.entity.GarageItem;
import garage.wangyu.com.garageandroid.enums.GarageItemStatusEnum;

/**
 * 车位分布
 */
public class GarageAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<GarageItem> garageItemList;

    public GarageAdapter() {
    }

    public GarageAdapter(Context mContext, List<GarageItem> garageItemList) {
        this.mContext = mContext;
        this.garageItemList = garageItemList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return garageItemList.size();
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
        public TextView tvCode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.garage_item, null);
            holder = new ViewHolder();
            holder.tvCode = convertView.findViewById(R.id.tv_gi_code);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GarageItem item = this.garageItemList.get(position);

        holder.tvCode.setText(item.getCode());

        if(item.getStatus() == GarageItemStatusEnum.NO_CAR.value()){
//            convertView.setBackgroundColor(0xFF0000);
            convertView.setBackgroundColor(Color.parseColor("#00FF00"));
//            holder.tvCode.setBackgroundColor(0xFF0000);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FF0000"));
//            convertView.setBackgroundColor(0x00FF00);
//            holder.tvCode.setBackgroundColor(0x00FF00);
        }

//        holder.tvIndex.setText((position + 1) + "");


        return convertView;
    }

}
