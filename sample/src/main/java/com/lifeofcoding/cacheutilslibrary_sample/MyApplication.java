package com.lifeofcoding.cacheutilslibrary_sample;

import android.app.Application;

import com.lifeofcoding.cacheutlislibrary.CacheUtils;

/**
 * Created by Wesley Lin on 9/5/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // configure CacheUtilsLibrary
        CacheUtils.configureCache(this);
    }
}
