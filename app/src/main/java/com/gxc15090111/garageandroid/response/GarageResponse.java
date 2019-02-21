package com.gxc15090111.garageandroid.response;

import com.gxc15090111.common.response.BaseResponse;

import com.gxc15090111.garageandroid.entity.Garage;

/**
 * @Description
 * @Author gxc15090111
 * @Date 2018/12/24 1:18
 */
public class GarageResponse extends BaseResponse {

    private Garage garage;


    public GarageResponse() {

    }

    public GarageResponse(Garage garage) {
        this.garage = garage;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

}
