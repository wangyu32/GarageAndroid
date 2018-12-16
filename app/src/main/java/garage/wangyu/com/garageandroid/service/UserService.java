package garage.wangyu.com.garageandroid.service;


import com.alibaba.fastjson.JSON;
import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.dto.UserChangePasswordDTO;
import garage.wangyu.com.garageandroid.dto.UserLoginDTO;
import garage.wangyu.com.garageandroid.dto.UserQueryDTO;
import garage.wangyu.com.garageandroid.dto.UserRegisterDTO;
import garage.wangyu.com.garageandroid.entity.User;
import garage.wangyu.com.garageandroid.util.HttpUtils;
import garage.wangyu.com.garageandroid.util.PropertiesUtils;

public class UserService {

    //车库ID
    public static Long GARAGE_ID = Long.valueOf(PropertiesUtils.getProperties("garageId"));

    //服务地址
    public static String SERVICE_URL = PropertiesUtils.getProperties("serviceUrl");

    //用户注册
    public static String REGISTER = SERVICE_URL + "/user/register";

    //用户登录
    public static String LOGIN = SERVICE_URL + "/user/login";

    //修改密码
    public static String CHANGE_PASSWORD = SERVICE_URL + "/user/changePassword";

    //根据手机号查询用户
    public static String GET_USER_BY_PHONE = SERVICE_URL + "/user/getUserByPhone";

    //入库二维码
    public static String COME_IN_QR_CODE = SERVICE_URL + "/garage/comein";

    //出库二维码
    public static String COME_OUT_QR_CODE = SERVICE_URL + "/garage/comeout";

    /**
     * 注册
     * @param dto
     * @return
     * @throws Exception
     */
    public Result register(UserRegisterDTO dto) throws Exception{
        try {
            String json = HttpUtils.postJson(REGISTER, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * 登录
     * @param dto
     * @return
     * @throws Exception
     */
    public Result login(UserLoginDTO dto) throws Exception{
        try {
            String json = HttpUtils.postJson(LOGIN, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * 修改密码
     * @param dto
     * @return
     * @throws Exception
     */
    public Result changePassword(UserChangePasswordDTO dto) throws Exception{
        try {
            String json = HttpUtils.postJson(CHANGE_PASSWORD, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * 根据手机号查询用户
     * @return
     * @throws Exception
     */
    public Result getUserByPhone(String phone) throws Exception{
        try {
            UserQueryDTO dto = new UserQueryDTO();
            dto.setPhone(phone);
            String json = HttpUtils.postJson(GET_USER_BY_PHONE, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * 获取入库扫描二维码
     * @return
     */
    public String getComeinQrCode(){
        return COME_IN_QR_CODE;
    }

    /**
     * 获取出库扫描二维码
     * @return
     */
    public String getComeoutQrCode(){
        return COME_OUT_QR_CODE;
    }

}
