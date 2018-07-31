package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})
public interface Environment extends Config {

        @Key("baseurl.path")
        String baseUrl();
}
