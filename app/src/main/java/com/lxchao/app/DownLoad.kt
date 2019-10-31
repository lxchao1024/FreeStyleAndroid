package com.lxchao.app

import android.content.Context
import android.util.Log
import com.lxchao.app.ui.activity.MainActivity
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import okhttp3.Call
import okhttp3.Request
import java.io.File
import java.lang.Exception

/**
 *
 * @author lixiangchao
 * @date 2019/8/26
 * @version 1.0.0
 */
fun download(context: Context, position: Int, url: String, callBack: MainActivity.OnMainCallBack?) {
    OkHttpUtils.get().tag(url).url(url).build().execute(object : FileCallBack(context.cacheDir.absolutePath, "${System.currentTimeMillis()}.jpg") {
        override fun inProgress(progress: Float, total: Long, id: Int) {
            super.inProgress(progress, total, id)
            callBack?.let { it.onProgress(progress) }
            Log.e("sss", "download position = " + position + "\tprogress = " + progress)
        }

        override fun onError(call: Call?, e: Exception?, id: Int) {
            Log.e("sss", "on error====")
        }

        override fun onResponse(response: File?, id: Int) {
            Log.e("sss", "on response====")
        }

        override fun onBefore(request: Request?, id: Int) {
            super.onBefore(request, id)
            Log.e("sss", "start sssss ==== ")
        }
    })
}