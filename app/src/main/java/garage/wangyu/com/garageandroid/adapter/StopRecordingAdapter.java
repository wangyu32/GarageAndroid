package garage.wangyu.com.garageandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import garage.wangyu.com.garageandroid.activitys.R;
import garage.wangyu.com.garageandroid.entity.StopRecording;
import garage.wangyu.com.garageandroid.service.UserService;

public class StopRecordingAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<StopRecording> stopRecordingsList;

    private UserService userService;

    private DateFormat formator = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public StopRecordingAdapter() {
    }

    public StopRecordingAdapter(Context context, List<StopRecording> stopRecordingsList) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.stopRecordingsList = stopRecordingsList;
        userService = new UserService();
    }

    @Override
    public int getCount() {
        //列表长度
        return stopRecordingsList.size();
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
        public TextView tvIndex, tvIntime, tvOuttime, tvTotal, tvAmount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //列表没一个行内容使用getView
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.stoprecording_list_item, null);
            holder = new ViewHolder();
            holder.tvIndex = convertView.findViewById(R.id.s_index);
            holder.tvIntime = convertView.findViewById(R.id.s_intime);
            holder.tvOuttime = convertView.findViewById(R.id.s_outtime);
            holder.tvTotal = convertView.findViewById(R.id.s_total);
            holder.tvAmount = convertView.findViewById(R.id.s_amount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        StopRecording stopRecording = stopRecordingsList.get(position);

        holder.tvIndex.setText((position + 1) + "");
        if (stopRecording.getIntime() != null) {
            holder.tvIntime.setText("入库：" + formator.format(stopRecording.getIntime()));
        } else {
            holder.tvIntime.setText("入库：");
        }

        if (stopRecording.getOuttime() != null) {
            holder.tvOuttime.setText("出库：" + formator.format(stopRecording.getOuttime()));
        } else {
            holder.tvOuttime.setText("出库：");
        }

        if (stopRecording.getTotaltime() != null) {
            holder.tvTotal.setText("计时：" + userService.getDateDiff4Chinese(stopRecording.getTotaltime()));
        } else {
            holder.tvTotal.setText("计时：");
        }

        if (stopRecording.getAmount() != null) {
            holder.tvAmount.setText("计费：" + stopRecording.getAmount().toString());
        } else {
            holder.tvAmount.setText("计费：");
        }

        return convertView;
    }
}
