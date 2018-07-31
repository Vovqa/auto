package config;

import org.aeonbits.owner.ConfigFactory;

public class TestConfig {

    public static String getTestEnvType() {
        return System.getProperty("env", "dev");
    }

    public static String getBaseUrl() {
        ConfigFactory.setProperty("env", getTestEnvType());
        Environment testEnv = ConfigFactory.create(Environment.class);
        return testEnv.baseUrl();
    }
}
