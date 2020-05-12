package com.lxchao.myexcel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/18
 */
public class App extends Application {

    public static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Utils.copy(getApplicationContext(), "simulation/danxuan.xls", getExternalCacheDir().getPath() + "/simulation/danxuan.xls");
                    Utils.copy(getApplicationContext(), "simulation/duoxuan.xls", getExternalCacheDir().getPath() + "/simulation/duoxuan.xls");
                    Utils.copy(getApplicationContext(), "simulation/panduan.xls", getExternalCacheDir().getPath() + "/simulation/panduan.xls");

                    Utils.copy(getApplicationContext(), "digit/danxuan.xls", getExternalCacheDir().getPath() + "/digit/danxuan.xls");
                    Utils.copy(getApplicationContext(), "digit/duoxuan.xls", getExternalCacheDir().getPath() + "/digit/duoxuan.xls");
                    Utils.copy(getApplicationContext(), "digit/panduan.xls", getExternalCacheDir().getPath() + "/digit/panduan.xls");
                    Utils.copy(getApplicationContext(), "digit/tiankong.xls", getExternalCacheDir().getPath() + "/digit/tiankong.xls");
                    Utils.copy(getApplicationContext(), "digit/wenda.xls", getExternalCacheDir().getPath() + "/digit/wenda.xls");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("sss", "copy exception");
                }
            }
        }).run();
    }
}
