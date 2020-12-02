package com.lsf.studymybatis.config;


public class MyDataSourceHandler {
    private static ThreadLocal<String> handlerThredLocal = new ThreadLocal<String>();

    public static void putDataSource(String dataSource) {
        handlerThredLocal.set(dataSource);
    }

    public static String getDataSource() {
        return handlerThredLocal.get();
    }

    public static void remove() {
        handlerThredLocal.remove();
    }
}
