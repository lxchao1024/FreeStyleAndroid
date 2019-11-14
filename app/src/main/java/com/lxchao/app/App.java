package com.lxchao.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/12
 */
public class App extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
