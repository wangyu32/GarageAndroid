package com.gxc15090111.garageandroid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gxc15090111.common.Result;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.gxc15090111.garageandroid.entity.StopRecording;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);？

        String json = "{\"success\":true,\"code\":\"0\",\"message\":null,\"data\":{\"id\":18,\"garageid\":1,\"userid\":9,\"carNumber\":null,\"phone\":\"13900090009\",\"intime\":1544951383000,\"outtime\":1545143763067,\"totaltime\":8995,\"amount\":384760.134,\"status\":1}}";

        Result result = JSON.parseObject(json, Result.class);
        DateFormat formator = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(result.isSuccess()){
            //将扫描出的信息显示出来
            StopRecording stopRedcording = null;
            try {
                stopRedcording = convertJSONObjectToStopRecording((JSONObject)result.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }

            StringBuffer sb = new StringBuffer();
            sb.append("状    态：").append("出库成功").append("\n");
//            sb.append("用    户：").append(user.getName()).append("\n");
//            sb.append("手机号码：").append(user.getPhone()).append("\n");
            sb.append("入库时间：").append(formator.format(stopRedcording.getIntime())).append("\n");
            sb.append("出库时间：").append(formator.format(stopRedcording.getOuttime())).append("\n");
            sb.append("停车时间：").append(getDateDiff4Chinese(stopRedcording.getTotaltime())).append("\n");
            sb.append("停车费用：").append(stopRedcording.getAmount()).append("元").append("\n");
            String stopRedcordingView = sb.toString();
            //TODO 更好的显示给前端
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("状    态：").append("出库失败：").append(result.getMessage()).append("\n");
//            sb.append("用    户：").append(user.getName()).append("\n");
//            sb.append("手机号码：").append(user.getPhone()).append("\n");
        }

    }

    private String getDateDiff4Chinese(long diff) {
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
            bean.setStatus(jsonObject.getInteger("status"));
            bean.setTotaltime(jsonObject.getLong("totaltime"));
            bean.setAmount(jsonObject.getBigDecimal("amount"));
            return bean;
        } catch (Exception e) {
            throw e;
        }
    }

}