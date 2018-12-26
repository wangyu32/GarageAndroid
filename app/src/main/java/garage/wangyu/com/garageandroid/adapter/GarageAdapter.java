package garage.wangyu.com.garageandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import garage.wangyu.com.garageandroid.service.UserService;

/**
 * 车位分布
 */
public class GarageAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private UserService userService;

    public GarageAdapter() {

    }

    public GarageAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        userService = new UserService();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
