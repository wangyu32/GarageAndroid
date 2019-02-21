package com.gxc15090111.garageandroid.parameter;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 停车记录查询参数
 * @Author gxc15090111
 * @Date 2018/12/6 19:22
 */
@Data
public class StopRecordingQueryParameter {

    /**
     * 车库ID
     */
    private Long garageid;

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 车主电话
     */
    private String phone;

    /**
     * 进入车库时间
     */
    private Date intime;

    /**
     * 离开车库时间
     */
    private Date outtime;

    /**
     * 停车费
     */
    private BigDecimal amount;

    /**
     * 状态0-入库;1-出库
     */
    private Integer status;


}
