package garage.wangyu.com.garageandroid.util;

import java.util.HashMap;
import java.util.Map;

public class HttpUtilTest {

    public static void main(String[] args) throws Exception {
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
