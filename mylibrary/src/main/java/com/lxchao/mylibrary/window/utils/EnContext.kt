package com.lxchao.mylibrary.window.utils

import android.app.Application

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/9/30
 */
object EnContext {

    private val INSTANCE: Application?
    init {
        var app: Application? = null
        try {
            app = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null) as Application
            if (app == null) {
                throw IllegalStateException("Static initialization of Applications must be on main thread.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            try {
                app = Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null) as Application
            } catch (ex: Exception) {
                e.printStackTrace()
            }
        } finally {
            INSTANCE = app
        }
    }
}
