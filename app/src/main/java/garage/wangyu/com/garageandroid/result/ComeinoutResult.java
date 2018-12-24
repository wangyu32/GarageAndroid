package garage.wangyu.com.garageandroid.result;

import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.vo.ComeinoutVO;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/25 1:57
 */
public class ComeinoutResult extends Result {

    private ComeinoutVO data;

    @Override
    public ComeinoutVO getData() {
        return data;
    }

    public void setData(ComeinoutVO data) {
        this.data = data;
    }
}
