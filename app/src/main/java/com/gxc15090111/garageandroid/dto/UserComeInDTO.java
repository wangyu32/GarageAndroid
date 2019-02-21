package com.gxc15090111.garageandroid.dto;

import com.gxc15090111.common.validate.ValidateResult;
import lombok.Data;

/**
 * @Description 入库DTO
 * @Author gxc15090111
 * @Date 2018/12/6 17:14
 */
@Data
public class UserComeInDTO {

    /**
     * 车库ID
     */
    private Long garageId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 校验参数
     * @return
     */
    public ValidateResult validate() {
        if(garageId == null)
            return ValidateResult.INVALID.setMessage("车库不能为空");

        if(userId == null)
            return ValidateResult.INVALID.setMessage("用户不能为空");

        return ValidateResult.VALID;
    }

}
