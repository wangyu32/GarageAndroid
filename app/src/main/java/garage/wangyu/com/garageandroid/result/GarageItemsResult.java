package garage.wangyu.com.garageandroid.result;

import com.wangyu.common.Result;

import java.util.List;

import garage.wangyu.com.garageandroid.entity.GarageItem;

public class GarageItemsResult extends Result {

    List<GarageItem> data;

    @Override
    public List<GarageItem> getData() {
        return data;
    }

    public void setData(List<GarageItem> data) {
        this.data = data;
    }
}
