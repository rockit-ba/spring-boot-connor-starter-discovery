package cn.pan.connor.common.utils;

import com.google.gson.GsonBuilder;

/**
 * Gson 序列化
 * 方便指定序列化的名称（java遵循单词驼峰，rust遵循下划线托fun）
 * @author Lucky Pan
 * @date 2022/4/17 16:14
 */
public class JsonUtil {
    private final static GsonBuilder GSON = new GsonBuilder();
    static {
        GSON.disableHtmlEscaping();
    }

    public static <T> T toBean(String json, Class<T> tClass) {
        return GSON.create().fromJson(json,tClass);
    }

    public static String toStr(Object pojo) {
        return GSON.create().toJson(pojo);
    }
}
