package cn.pan.connor.common.consts;

import com.google.gson.GsonBuilder;

/**
 * @author Lucky Pan
 * @date 2022/4/17 16:14
 */
public class JsonUtil {
    static GsonBuilder gson = new GsonBuilder();
    static {
        gson.disableHtmlEscaping();
    }

    public static <T> T json2pojo(String json, Class<T> tClass) {
        return gson.create().fromJson(json,tClass);
    }

    public static String pojo2json(Object pojo) {
        return gson.create().toJson(pojo);
    }
}
