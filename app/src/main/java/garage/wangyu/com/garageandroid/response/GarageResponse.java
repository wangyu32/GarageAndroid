package garage.wangyu.com.garageandroid.response;

import com.wangyu.common.response.BaseResponse;

import garage.wangyu.com.garageandroid.entity.Garage;

/**
 * @Description
 * @Author wangyu
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
