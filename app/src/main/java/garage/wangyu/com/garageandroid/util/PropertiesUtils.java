package garage.wangyu.com.garageandroid.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static Properties properties = null;

    private static synchronized void readProperties() {
        try {
            InputStream in = PropertiesUtils.class.getResourceAsStream("/assets/config.properties");
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Log.e("PropertiesUtils", "get nothing");
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        if (properties == null) {
            readProperties();
        }
        return properties;
    }

    public static String getProperties(String name) {
        if (properties == null) {
            readProperties();
        }
        return (String)properties.get(name);
    }


}
