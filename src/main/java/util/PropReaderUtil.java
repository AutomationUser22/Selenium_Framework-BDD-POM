package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropReaderUtil {

    private Properties propLoad() {
        Properties props = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/main/java/config/config.properties");
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

    public String getBrowser() {
        return propLoad().getProperty("browser");
    }

    public String getChromeOptions() {
        return propLoad().getProperty("chromeOptions");
    }

    public long getDefaultImplicitWaitInSeconds() {
        return Long.parseLong(propLoad().getProperty("defaultImplicitWaitInSeconds"));
    }

    public String getSelectedProductText() {
        return propLoad().getProperty("selectedProductText");
    }

    public long getDefaultExplicitWaitInSeconds() {
        return Long.parseLong(propLoad().getProperty("defaultExplicitWaitInSeconds"));
    }

    public String getOrderConfirmationText() {
        return propLoad().getProperty("orderConfirmationText");
    }

    public String getOrderCountry() {
        return propLoad().getProperty("orderCountry");
    }
}
