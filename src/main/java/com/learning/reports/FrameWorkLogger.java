package com.learning.reports;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.learning.enums.LogType;


import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String;

/**
 * The FrameWorkLogger class provides methods for logging different types of messages using ExtentManager.
 * It uses Consumers to define the actions to be performed for each log type.
 */
public class FrameWorkLogger {
    // Consumers for different log types
    private static final Consumer<String> PASS = (message) -> ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    private static final Consumer<String> FAIL = (message) -> ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    private static final Consumer<String> INFO = (message) -> ExtentManager.getExtentTest().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
    private static final Consumer<String> SKIP = (message) -> ExtentManager.getExtentTest().skip(MarkupHelper.createLabel(message, ExtentColor.PINK));
    private static final Consumer<String> CONSOLE = System.out::println;
    private static final Consumer<String> EXTENT_AND_CONSOLE = PASS.andThen(CONSOLE);

    // Map to associate log types with their respective consumers
    private static final Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);
    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.EXTENT_AND_CONSOLE, EXTENT_AND_CONSOLE);
    }

    /**
     * Private constructor to prevent instantiation of the FrameWorkLogger class.
     */
    private FrameWorkLogger() {
    }

    /**
     * Logs a message of the specified log type using the associated consumer.
     * @param logType The log type.
     * @param message The message to be logged.
     * @return The logged message.
     */
    public static String log(LogType logType, String message) {
        MAP.get(logType).accept(message);
        return message;
    }
}
