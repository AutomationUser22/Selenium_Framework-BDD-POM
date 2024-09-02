package main.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropReaderUtil {

    private Properties propLoad() {
        Properties props = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/config/config.properties");
            props = new Properties();
            props.load(fileInputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return props;
    }

    public String getBaseURL() {

        return propLoad().getProperty("baseURL");
    }

    public String getUserName() {
        return propLoad().getProperty("userName");
    }

    public String getPassword() {
        return propLoad().getProperty("password");
    }

}
