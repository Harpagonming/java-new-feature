package com.zhenming.demo.application.interfaces;

/**
 * 接口允许私有方法
 */
public interface PrivateMethodInterface {
    private void log(String message, String prefix) {
        getConnection();
        System.out.println("Log Message: " + prefix + " => " + message);
        closeConnection();
    }

    default void logInfo(String message) {
        log(message, "INFO");
    }

    default void logWarn(String message) {
        log(message, "WARN");
    }

    default void logError(String message) {
        log(message, "ERROR");
    }

    default void logFatal(String message) {
        log(message, "FATAL");
    }

    private static void getConnection() {
        System.out.println("Open Database connection");
    }

    private static void closeConnection() {
        System.out.println("Close Database connection");
    }
}
