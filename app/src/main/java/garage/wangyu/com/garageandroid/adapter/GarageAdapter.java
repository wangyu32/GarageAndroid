package garage.wangyu.com.garageandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import garage.wangyu.com.garageandroid.activitys.R;
import garage.wangyu.com.garageandroid.entity.GarageItem;
import garage.wangyu.com.garageandroid.entity.StopRecording;
import garage.wangyu.com.garageandroid.enums.GarageItemStatusEnum;

/**
 * 车位分布
 */
public class GarageAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<GarageItem> garageItemList;
    private Map<Long, Integer> garageItemIndexMap = new HashMap<>();
    private StopRecording stopRecording;

    private boolean isAllCarView;//是否查看全部查询全部车辆分别，如果是就是查看自己的车位

    public GarageAdapter() {
    }

    public GarageAdapter(Context mContext, List<GarageItem> garageItemList, StopRecording stopRecording, boolean isAllCarView) {
        this.mContext = mContext;
        this.garageItemList = garageItemList;
        this.isAllCarView = isAllCarView;
        this.stopRecording = stopRecording;

        for (int i = 0; i < this.garageItemList.size(); i++) {
            GarageItem item = this.garageItemList.get(i);
            garageItemIndexMap.put(item.getId(), i);
        }


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

        if(isAllCarView){
            //查看全部车位分布
            if(item1.getStatus() == GarageItemStatusEnum.NO_CAR.value()){
                holder.tvCode1.setBackgroundColor(Color.parseColor("#00FF00"));//绿色
            } else {
                holder.tvCode1.setBackgroundColor(Color.parseColor("#FF0000"));//红色
            }
            if(item2.getStatus() == GarageItemStatusEnum.NO_CAR.value()){
                holder.tvCode2.setBackgroundColor(Color.parseColor("#00FF00"));
            } else {
                holder.tvCode2.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        } else {
            if(stopRecording != null){
                Long itemId = stopRecording.getItemId();
                if(itemId == item1.getId()){
                    holder.tvCode1.setText(item1.getCode() + " 我的汽车");
                    holder.tvCode1.setBackgroundColor(Color.parseColor("#FF0000"));//红色
                } else {
                    holder.tvCode1.setBackgroundColor(Color.parseColor("#00FF00"));//绿色
                }

                if (itemId == item2.getId()){
                    holder.tvCode2.setText(item2.getCode() + " 我的汽车");
                    holder.tvCode2.setBackgroundColor(Color.parseColor("#FF0000"));
                } else {
                    holder.tvCode2.setBackgroundColor(Color.parseColor("#00FF00"));
                }
            } else {

            }
        }

        return convertView;
    }

}
