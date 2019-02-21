package com.gxc15090111.garageandroid.dto;

import com.gxc15090111.common.validate.ValidateResult;

import com.gxc15090111.garageandroid.util.NullUtil;
import lombok.Data;

/**
 * @Description 修改密码
 * @Author gxc15090111
 * @Date 2018/12/6 16:34
 */
@Data
public class UserChangePasswordDTO {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 新密码
     */
    private String newPasswordConfirm;

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword == null ? null : oldPassword.trim();
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword == null ? null : newPassword.trim();
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm == null ? null : newPasswordConfirm.trim();
    }

    /**
     * 校验参数
     * @return
     */
    public ValidateResult validate() {
        if(NullUtil.isNull(phone)){
            return ValidateResult.INVALID.setMessage("手机号码不能为空");
        }

        if(NullUtil.isNull(oldPassword)){
            return ValidateResult.INVALID.setMessage("旧密码不能为空");
        }

        if(NullUtil.isNull(oldPassword)){
            return ValidateResult.INVALID.setMessage("新密码不能为空");
        }

        if(NullUtil.isNull(newPasswordConfirm)){
            return ValidateResult.INVALID.setMessage("确认密码不能为空");
        }

        if(oldPassword.equals(newPassword)){
            return ValidateResult.INVALID.setMessage("新密码不能与旧密码相同");
        }

        if(!newPassword.equals(newPasswordConfirm)){
            return ValidateResult.INVALID.setMessage("新密码两次输入必须相同");
        }

        return ValidateResult.VALID;
    }
}
