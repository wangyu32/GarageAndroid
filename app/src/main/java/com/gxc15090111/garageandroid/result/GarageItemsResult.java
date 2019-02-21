package com.gxc15090111.garageandroid.result;

import com.gxc15090111.common.Result;

import java.util.List;

import com.gxc15090111.garageandroid.entity.GarageItem;

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
