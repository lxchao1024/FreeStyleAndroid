package com.lxchao.myexcel;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/19
 */
public class SpUtils {

    private static final String spName = "all_pos";

    public static final String KEY_DIGIT_SINGLE = "digit_single";
    public static final String KEY_DIGIT_CHECKED = "digit_checked";
    public static final String KEY_DIGIT_OPINION = "digit_opinion";
    public static final String KEY_DIGIT_BLANK = "digit_blank";
    public static final String KEY_DIGIT_QA = "digit_qa";

    public static final String KEY_SIMULATION_SINGLE = "simulation_single";
    public static final String KEY_SIMULATION_CHECKED = "simulation_checked";
    public static final String KEY_SIMULATION_OPINION = "simulation_opinion";

    public static void put(String key, int value) {
        SharedPreferences sharedPreferences = App.context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static int get(String key) {
        SharedPreferences sharedPreferences = App.context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }
}
