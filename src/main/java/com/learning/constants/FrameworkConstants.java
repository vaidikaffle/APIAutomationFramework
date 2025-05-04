package com.learning.constants;

import com.learning.enums.PropertiesType;
import com.learning.utils.PropertyUtils;
import lombok.Getter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FrameworkConstants {

    // If it is non-static --> Class level

    private static final String userDirectory = System.getProperty("user.dir");
    private static @Getter final String requestJsonFolderPath = userDirectory + "/src/test/resources/jsonFiles/";
    private static @Getter final String responseJsonFolderPath = userDirectory + "/output/";
    private static @Getter final String configFilePath = userDirectory + "/src/test/resources/config/config.properties";
    private static @Getter final String jsonSchemaFilePath   = userDirectory + "/src/test/resources/jsonSchema/";
    private static final String EXTENTREPORTPATH = System.getProperty("user.dir") + "/extent-test-output";

    public static String getEXTENTREPORTPATH() {
        if (PropertyUtils.getPropertyValue("overrideReports").equalsIgnoreCase("yes")) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);
            return EXTENTREPORTPATH + "/ExtentReport_" + timestamp + "/index.html";
        } else {
            return EXTENTREPORTPATH + "/index.html";
        }
    }

}
