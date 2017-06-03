package fi.hockeyseer.config;

import org.springframework.boot.SpringApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alekstu on 1.6.2017.
 */
public class ProfileUtil {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private ProfileUtil(){}


    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defaultProperties = new HashMap<>();

        defaultProperties.put(SPRING_PROFILE_DEFAULT, Constants.SPRING_PROFILE_DEVELOPMENT);
        app.setDefaultProperties(defaultProperties);
    }
}
