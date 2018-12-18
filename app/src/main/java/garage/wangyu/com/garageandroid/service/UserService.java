package garage.wangyu.com.garageandroid.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangyu.common.Result;

import garage.wangyu.com.garageandroid.dto.UserChangePasswordDTO;
import garage.wangyu.com.garageandroid.dto.UserComeInDTO;
import garage.wangyu.com.garageandroid.dto.UserLoginDTO;
import garage.wangyu.com.garageandroid.dto.UserQueryDTO;
import garage.wangyu.com.garageandroid.dto.UserRegisterDTO;
import garage.wangyu.com.garageandroid.entity.StopRecording;
import garage.wangyu.com.garageandroid.entity.User;
import garage.wangyu.com.garageandroid.enums.CarStatusEnum;
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
     * 获取车库ID
     *
     * @return
     */
    public Long getGarageId() {
        return GARAGE_ID;
    }

    /**
     * 注册
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public Result register(UserRegisterDTO dto) throws Exception {
        try {
            String json = HttpUtils.postJson(REGISTER, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 登录
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public Result login(UserLoginDTO dto) throws Exception {
        try {
            String json = HttpUtils.postJson(LOGIN, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 修改密码
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public Result changePassword(UserChangePasswordDTO dto) throws Exception {
        try {
            String json = HttpUtils.postJson(CHANGE_PASSWORD, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 根据手机号查询用户
     *
     * @return
     * @throws Exception
     */
    public Result getUserByPhone(String phone) throws Exception {
        try {
            UserQueryDTO dto = new UserQueryDTO();
            dto.setPhone(phone);
            String json = HttpUtils.postJson(GET_USER_BY_PHONE, JSON.toJSONString(dto));
            Result result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取入库扫描二维码
     *
     * @return
     */
    public String getComeinQrCodeUrl() {
        return COME_IN_QR_CODE;
    }

    /**
     * 获取出库扫描二维码
     *
     * @return
     */
    public String getComeoutQrCodeUrl() {
        return COME_OUT_QR_CODE;
    }

    /**
     * 扫描入库
     */
    public Result comein(String phone) throws Exception {
        try {
            Result result = getUserByPhone(phone);
            User user = (User) result.getData();
            UserComeInDTO dto = new UserComeInDTO();
            dto.setGarageId(getGarageId());
            dto.setUserId(user.getId());
            String json = HttpUtils.postJson(COME_IN_QR_CODE, JSON.toJSONString(dto));
            result = JSON.parseObject(json, Result.class);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public User convertJSONObjectToUser(JSONObject jsonObject) throws Exception {
        try {
            if (jsonObject == null)
                return null;
            User user1 = new User();
            user1.setId(jsonObject.getLong("id"));
            user1.setName(jsonObject.getString("name"));
            user1.setSex(jsonObject.getInteger("sex"));
            user1.setPhone(jsonObject.getString("phone"));
            user1.setPassword(jsonObject.getString("password"));
            user1.setType(jsonObject.getInteger("type"));
            user1.setPrice(jsonObject.getBigDecimal("price"));
            user1.setCreatetime(jsonObject.getDate("createtime"));
            return user1;
        } catch (Exception e) {
            throw e;
        }
    }

    public StopRecording convertJSONObjectToStopRecording(JSONObject jsonObject) throws Exception {
        try {
            if (jsonObject == null)
                return null;
            StopRecording bean = new StopRecording();
            bean.setId(jsonObject.getLong("id"));
            bean.setGarageid(jsonObject.getLong("garageid"));
            bean.setUserid(jsonObject.getLong("userid"));
            bean.setCarNumber(jsonObject.getString("carNumber"));
            bean.setPhone(jsonObject.getString("phone"));
            bean.setIntime(jsonObject.getDate("intime"));
            bean.setOuttime(jsonObject.getDate("outtime"));
            bean.setTotaltime(jsonObject.getLong("totaltime"));
            bean.setStatus(jsonObject.getInteger("status"));
            bean.setAmount(jsonObject.getBigDecimal("amount"));
            return bean;
        } catch (Exception e) {
            throw e;
        }
    }

    //TODO 查询停车记录
}
