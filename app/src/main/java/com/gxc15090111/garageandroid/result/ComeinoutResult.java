package com.gxc15090111.garageandroid.result;

import com.gxc15090111.common.Result;

import com.gxc15090111.garageandroid.vo.ComeinoutVO;

/**
 * @Description
 * @Author gxc15090111
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
