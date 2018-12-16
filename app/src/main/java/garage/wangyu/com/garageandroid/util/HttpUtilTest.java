package garage.wangyu.com.garageandroid.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.wangyu.common.Result;

import java.util.HashMap;
import java.util.Map;

import garage.wangyu.com.garageandroid.dto.UserComeInDTO;
import garage.wangyu.com.garageandroid.dto.UserQueryDTO;
import garage.wangyu.com.garageandroid.entity.User;

public class HttpUtilTest {

    public static void main(String[] args) throws Exception {
//        testLogin();
        test1();
    }

    private static void test1() throws Exception{
        String url = "http://localhost:8080/garage/user/getUserByPhone";
        String url1 = "http://localhost:8080/garage/garage/comein";
        Gson gson = new Gson();

        UserQueryDTO dto = new UserQueryDTO();
        dto.setPhone("13900090009");
        String json = HttpUtils.postJson(url, JSON.toJSONString(dto));
        System.out.println(json);

        Result result = JSON.parseObject(json, Result.class);
        Result result2 = gson.fromJson(json, Result.class);

        JSONObject user = (JSONObject)result.getData();
        User user1 = new User();
        user1.setId(user.getLong("id"));
        user1.setName(user.getString("name"));
        user1.setSex(user.getInteger("sex"));
        user1.setPhone(user.getString("phone"));
        user1.setType(user.getInteger("type"));
        user1.setPrice(user.getBigDecimal("price"));
        user1.setCreatetime(user.getDate("createtime"));


        UserComeInDTO dto1 = new UserComeInDTO();
        dto1.setGarageId(1L);
        dto1.setUserId(Long.valueOf(user.get("id").toString()));

        User u = gson.fromJson(gson.toJson(result.getData()), User.class);

        String json1 = HttpUtils.postJson(url1, JSON.toJSONString(dto1));
        result = JSON.parseObject(json1, Result.class);
        System.out.println(JSON.toJSONString(result.getData()));
    }

    private static void testLogin() throws Exception{
        String url = "http://localhost:8080/garage/user/login";


        Map<String, String> params = new HashMap<>();
        String postJson = "{\n" +
                "\t\"phone\":\"13012345671\",\n" +
                "\t\"password\":\"2\"\n" +
                "}";
        String json = HttpUtils.postJson(url, postJson);
        System.out.println(json);
    }


}
