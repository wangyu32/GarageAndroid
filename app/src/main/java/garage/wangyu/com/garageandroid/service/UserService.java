package garage.wangyu.com.garageandroid.service;


import com.alibaba.fastjson.JSON;
import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.dto.UserChangePasswordDTO;
import garage.wangyu.com.garageandroid.dto.UserLoginDTO;
import garage.wangyu.com.garageandroid.dto.UserRegisterDTO;
import garage.wangyu.com.garageandroid.util.HttpUtils;
import garage.wangyu.com.garageandroid.util.PropertiesUtils;

public class UserService {

    public static String SERVICE_URL = PropertiesUtils.getProperties("serviceUrl");

    public static String REGISTER_URL = SERVICE_URL + "/user/register";

    public static String LOGIN_URL = SERVICE_URL + "/user/login";

    public static String CHANGE_PASSWORD_URL = SERVICE_URL + "/user/changePassword";

    /**
     * 注册
     * @param dto
     * @return
     * @throws Exception
     */
    public Result register(UserRegisterDTO dto) throws Exception{
        try {
            String json = HttpUtils.postJson(REGISTER_URL, JSON.toJSONString(dto));
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
            String json = HttpUtils.postJson(LOGIN_URL, JSON.toJSONString(dto));
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
            String json = HttpUtils.postJson(CHANGE_PASSWORD_URL, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e){
            throw e;
        }
    }


}
