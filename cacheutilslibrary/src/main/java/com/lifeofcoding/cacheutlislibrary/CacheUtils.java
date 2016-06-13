package com.lifeofcoding.cacheutlislibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wesley Lin on 9/5/15.
 */
public class CacheUtils {

    private static final String ENCODING = "utf8";
    private static final String FILE_SUFFIX = ".txt";
    public static String BASE_CACHE_PATH;

    private static final String TAG = "CACHE_UTILS";

    public static void configureCache(Context context) {
        BASE_CACHE_PATH = context.getApplicationInfo().dataDir + File.separator + "files" + File.separator + "CacheUtils";

        if (new File(BASE_CACHE_PATH).mkdirs()) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, BASE_CACHE_PATH + " created.");
        }
    }

    private static String pathForCacheEntry(String name) {
        return BASE_CACHE_PATH + File.separator + name + FILE_SUFFIX;
    }

    private static <T> List<Map<String, T>> dataMapsFromJson(String dataString) {
        if (TextUtils.isEmpty(dataString))
            return new ArrayList<Map<String, T>>();

        try {
            Type listType = new TypeToken<List<Map<String, T>>>(){}.getType();
            return GsonHelper.buildGson().fromJson(dataString, listType);
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "failed to read json" + e.toString());
            return new ArrayList<Map<String, T>>();
        }
    }

    private static <T> String dataMapstoJson(List<Map<String, T>> dataMaps) {
        try {
            return GsonHelper.buildGson().toJson(dataMaps);
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "failed to write json" + e.toString());
            return "[]";
        }
    }

    /**
     * @param fileName the name of the file
     * @return the content of the file, null if there is no such file
     */
    public static String readFile(String fileName) {
        try {
            return IOUtils.toString(new FileInputStream(pathForCacheEntry(fileName)), ENCODING);
        } catch (IOException e) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "read cache file failure" + e.toString());
            return null;
        }
    }

    /**
     * @param fileName the name of the file
     * @param fileContent the content of the file
     */
    public static void writeFile(String fileName, String fileContent) {
        try {
            IOUtils.write(fileContent, new FileOutputStream(pathForCacheEntry(fileName)), ENCODING);
        } catch (IOException e) {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "write cache file failure" + e.toString());
        }
    }

    /**
     * @param fileName the name of the file
     * @param dataMaps the map list you want to store
     */
    public static <T> void writeDataMapsFile(String fileName, List<Map<String, T>> dataMaps) {
        writeFile(fileName, dataMapstoJson(dataMaps));
    }

    /**
     * @param fileName the name of the file
     * @return the map list you previous stored, an empty {@link List} will be returned if there is no such file
     */
    public static <T> List<Map<String, T>> readDataMapsFile(String fileName) {
        return dataMapsFromJson(readFile(fileName));
    }

    private static <T> T objectFromJson(String dataString, Type t) {
        try {
            return GsonHelper.buildGson().fromJson(dataString, t);
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.v(TAG, "failed to read json" + e.toString());
            return null;
        }
    }

    private static <T> String objectToJson(T o) {
        try {
            return GsonHelper.buildGson().toJson(o);
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.v(TAG, "failed to write json" + e.toString());
            return null;
        }
    }

    /**
     * @param fileName the name of the file
     * @param object the object you want to store
     * @param <T> a class extends from {@link Object}
     */
    public static <T> void writeObjectFile(String fileName, T object) {
        writeFile(fileName, objectToJson(object));
    }

    /**
     * @param fileName the name of the file
     * @param t the type of the object you previous stored
     * @return the {@link T} type object you previous stored
     */
    public static <T> T readObjectFile(String fileName, Type t) {
        return objectFromJson(readFile(fileName), t);
    }

    private static <T> Map<String, T> dataMapFromJson(String dataString) {
        if (TextUtils.isEmpty(dataString))
            return new HashMap<String,T>();

        try {
            Type t =  new TypeToken<Map<String, T>>(){}.getType();
            return GsonHelper.buildGson().fromJson(dataString, t);
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.v(TAG, "failed to read json" + e.toString());
            return new HashMap<String,T>();
        }
    }

    private static <T> String dataMaptoJson(Map<String, T> dataMap) {
        try {
            return GsonHelper.buildGson().toJson(dataMap);
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.v(TAG, "failed to write json" + e.toString());
            return "{}";
        }
    }

    /**
     * @param fileName the name of the file
     * @param dataMap the map data you want to store
     */
    public static <T> void writeDataMapFile(String fileName, Map<String, T> dataMap) {
        writeFile(fileName, dataMaptoJson(dataMap));
    }

    /**
     * @param fileName the name of the file
     * @return the map data you previous stored
     */
    public static <T> Map<String, T> readDataMapFile(String fileName) {
        return dataMapFromJson(readFile(fileName));
    }

    /**
     * delete the file with fileName
     * @param fileName the name of the file
     */
    public static void deleteFile(String fileName) {
        FileUtils.deleteQuietly(new File(pathForCacheEntry(fileName)));
    }


    /**
     * check if there is a cache file with fileName
     * @param fileName the name of the file
     * @return true if the file exits, false otherwise
     */
    public static boolean hasCache(String fileName) {
        return new File(pathForCacheEntry(fileName)).exists();
    }
}
