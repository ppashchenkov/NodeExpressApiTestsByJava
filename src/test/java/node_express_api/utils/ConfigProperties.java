package node_express_api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigProperties {
    private static Properties properties = initProperties();

    public static final Map<String, String> ENVIROMENT_CHROMIUM = setEnviroment("browserName1", "isHeadLess1", "slowMo1");
    public static final Map<String, String> ENVIROMENT_FIREFOX = setEnviroment("browserName2", "isHeadLess2", "slowMo2");
    public static final Map<String, String> ENVIROMENT_WEBKIT = setEnviroment("browserName3", "isHeadLess3", "slowMo3");

    private static Properties initProperties() {
        properties = new Properties();
        try {
            FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
            properties.load(configFile);
        } catch (IOException e) {
            LoggerUtils.logError("ERROR: 'config.properties' file Not FOUND OR file is EMPTY or file is CORRUPT" );
            System.exit(1);
        }

        return properties;
    }

    private static Map<String, String> setEnviroment(String browser, String isHeadLess, String slowMo) {
        Map<String, String> enviroment = new HashMap<>();

        LoggerUtils.logInfo("properties = " + properties.toString());

        if(properties != null && !properties.isEmpty()
                && !properties.getProperty(browser).trim().isEmpty()
                && !properties.getProperty(isHeadLess).trim().isEmpty()
                && !properties.getProperty(slowMo).trim().isEmpty()
        ) {
            enviroment.put("browser", properties.getProperty(browser).trim());
            enviroment.put("isHeadLess", properties.getProperty(isHeadLess).trim());
            enviroment.put("slowMo", properties.getProperty(slowMo).trim());
        }else {
            LoggerUtils.logWarning("WARNING: Set DEFAULT enviroment.");

            enviroment.put("browser", "chromium");
            enviroment.put("isHeadLess", "true");
            enviroment.put("slowMo", "0");
        }

        return enviroment;
    }
}
