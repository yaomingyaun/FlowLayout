package com.bw.ymy.demo1;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.youth.banner.loader.ImageLoader;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(
                new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSizePercentage(10)
                .diskCacheSize(50*1024*1024)
                .build()
        );
    }
}
