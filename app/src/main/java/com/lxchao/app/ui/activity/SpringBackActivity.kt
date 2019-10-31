package com.lxchao.app.ui.activity

import android.app.AlertDialog
import android.app.AlertDialog.*
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.lxchao.app.R
import com.lxchao.app.dialog.InputTextMsgDialog
import kotlinx.android.synthetic.main.activity_spring_back.*

class SpringBackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring_back)

        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true

        webView.loadUrl("file:///android_asset/javascript.html");

        btn.setOnClickListener {
            webView.post {
                //first
                webView.loadUrl("javascript:callJS()")
                //second
                webView.evaluateJavascript("javascript:callJS()", ValueCallback {
                    Log.e("sss", "message === $it")
                })

                val md = InputTextMsgDialog(SpringBackActivity@this, R.style.dialog_center)
                md.setmOnTextSendListener {
                    Log.e("sss", it)
                }
                md.show()
            }
        }

        webView.webChromeClient = object: WebChromeClient() {
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                Log.e("sss", "message == $message")
                return true
            }
        }
    }
}
