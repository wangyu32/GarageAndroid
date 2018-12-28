package garage.wangyu.com.garageandroid.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangyu.common.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import garage.wangyu.com.garageandroid.dto.UserChangePasswordDTO;
import garage.wangyu.com.garageandroid.dto.UserComeInDTO;
import garage.wangyu.com.garageandroid.dto.UserLoginDTO;
import garage.wangyu.com.garageandroid.dto.UserQueryDTO;
import garage.wangyu.com.garageandroid.dto.UserRegisterDTO;
import garage.wangyu.com.garageandroid.entity.Garage;
import garage.wangyu.com.garageandroid.entity.GarageItem;
import garage.wangyu.com.garageandroid.entity.StopRecording;
import garage.wangyu.com.garageandroid.entity.User;
import garage.wangyu.com.garageandroid.parameter.StopRecordingQueryParameter;
import garage.wangyu.com.garageandroid.response.GarageResponse;
import garage.wangyu.com.garageandroid.result.GarageItemsResult;
import garage.wangyu.com.garageandroid.util.HttpUtils;
import garage.wangyu.com.garageandroid.util.PropertiesUtils;

public class UserService {

    //车库ID
    public static Long GARAGE_ID = Long.valueOf(PropertiesUtils.getProperties("garageId"));

    //基础服务地址
    public static String SERVICE_URL = PropertiesUtils.getProperties("serviceUrl");

    //车库服务地址
    public static String GARAGE_SERVICE_URL = null;

    //用户注册
    public static String REGISTER = GARAGE_SERVICE_URL + "/user/register";

    //用户登录
    public static String LOGIN = GARAGE_SERVICE_URL + "/user/login";

    //修改密码
    public static String CHANGE_PASSWORD = GARAGE_SERVICE_URL + "/user/changePassword";

    //根据手机号查询用户
    public static String GET_USER_BY_PHONE = GARAGE_SERVICE_URL + "/user/getUserByPhone";

    //入库二维码
    public static String COME_IN_QR_CODE = GARAGE_SERVICE_URL + "/garage/comein";

    //出库二维码
    public static String COME_OUT_QR_CODE = GARAGE_SERVICE_URL + "/garage/comeout";

    //出库二维码
    public static String STOP_RECORDING = GARAGE_SERVICE_URL + "/garage/queryStopRecording";

    //出库二维码
    public static String QUERY_ALL_GARAGE_ITEMS = GARAGE_SERVICE_URL + "/garage/queryAllGarageItem";

    public static boolean isDevelop = false;

    private UserService(){}

    private static UserService instacne;

    public static UserService getInstance(){
        if(instacne == null){
            instacne = new UserService();
        }
        return instacne;
    }

    static {
        //http://localhost:8080/garage/garage/query?id=1
    }

    public void init(){
        try {
            String json = HttpUtils.get(SERVICE_URL + "/garage/query?id=" + GARAGE_ID);
            GarageResponse response = JSON.parseObject(json, GarageResponse.class);
            Garage garage = response.getGarage();
            GARAGE_SERVICE_URL = "http://" + garage.getServerIp() + ":" + garage.getServerPort() + "/garage";
            REGISTER = GARAGE_SERVICE_URL + "/user/register";
            LOGIN = GARAGE_SERVICE_URL + "/user/login";
            CHANGE_PASSWORD = GARAGE_SERVICE_URL + "/user/changePassword";
            GET_USER_BY_PHONE = GARAGE_SERVICE_URL + "/user/getUserByPhone";
            COME_IN_QR_CODE = GARAGE_SERVICE_URL + "/garage/comein";
            COME_OUT_QR_CODE = GARAGE_SERVICE_URL + "/garage/comeout";
            STOP_RECORDING = GARAGE_SERVICE_URL + "/garage/queryStopRecording";
            QUERY_ALL_GARAGE_ITEMS = GARAGE_SERVICE_URL + "/garage/queryAllGarageItem";

            if(!SERVICE_URL.equals(GARAGE_SERVICE_URL)){
                isDevelop = true;
            }
        } catch (Exception e){
            throw new RuntimeException("车库不存在");
        }
    }

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

    /**
     * 查询停车记录
     */
    public List<StopRecording> queryStopRecodingList(StopRecordingQueryParameter parameter) throws Exception{
        try {
            Map<String, String> map = new HashMap<>();
            map.put("userid", parameter.getUserid().toString());
            map.put("garageid", parameter.getGarageid().toString());
            map.put("status", parameter.getStatus().toString());
            String json = HttpUtils.get(STOP_RECORDING, map);
            Result result = JSON.parseObject(json, Result.class);
            List<StopRecording> list = new ArrayList<>();
            if(result.isSuccess()){
                JSONArray jsonArray = (JSONArray)result.getData();
                for(Object object : jsonArray){
                    JSONObject jsonObject = (JSONObject)object;
                    list.add(convertJSONObjectToStopRecording(jsonObject));
                }
            }
            return list;
        } catch (Exception e) {
            throw e;
        }
    }


    public List<GarageItem> queryAllGarageItems(Long garageid) throws Exception{
        try {
//            String url = "http://localhost:8081/garage/garage/queryAllGarageItem?garageid=1";
            String url = QUERY_ALL_GARAGE_ITEMS + "?garageid=" + garageid;
            String json = HttpUtils.get(url);
            GarageItemsResult result = JSON.parseObject(json, GarageItemsResult.class);
            return result.getData();
        } catch (Exception e) {
            throw e;
        }
    }

    public String getDateDiff4Chinese(long diff) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000 ;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;

        long second = diff % nd % nh % nm / ns;

        // 总计多少小时
        long totalHour = new Double(Math.ceil(diff / (nh * 1.0d))).longValue();

        StringBuffer sb = new StringBuffer();
        sb.append(totalHour).append("小时");
        sb.append(" (");
        if(day > 0){
            sb.append(day).append("天");
        }

        if(hour > 0){
            sb.append(hour).append("小时");
        }

        if(min > 0){
            sb.append(min).append("分钟");
        }

        sb.append(second).append("秒");

        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        UserService userService = new UserService();

        //查询停车记录
        StopRecordingQueryParameter parameter = new StopRecordingQueryParameter();
        parameter.setUserid(1L);
        parameter.setGarageid(1L);
        parameter.setStatus(1);

        List<StopRecording> list = userService.queryStopRecodingList(parameter);
    }

}
