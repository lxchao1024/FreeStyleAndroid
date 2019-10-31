package com.lxchao.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.webkit.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxchao.app.R
import com.lxchao.app.bean.MovieBean
import com.lxchao.app.download
import com.lxchao.app.ui.widgets.ResizeAndScreenClearLayout
import com.lxchao.app.utils.Utils
import com.lxchao.mylibrary.window.listener.MagnetViewListener
import com.lxchao.mylibrary.window.view.FloatingMagnetView
import com.lxchao.mylibrary.window.view.FloatingView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * http://git.jufan.tv/tangpengxiang/MarsLive.git
 */
class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: Adapter? = null
    private var gridLayoutManager: GridLayoutManager? = null
    val mUrl = "https://www.google.com"
    val downLoadUrl = "https://www.google.com"

    public var webView: WebView? = null

    private var flag = false

    val list = arrayListOf<String>(downLoadUrl, downLoadUrl, downLoadUrl, downLoadUrl, downLoadUrl)

    lateinit var dataSource: MutableList<MovieBean>

    private lateinit var mVideoParrent: RelativeLayout

    private var view: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVideoParrent = RelativeLayout(applicationContext)
        mVideoParrent.setBackgroundResource(R.drawable.li_room_bg)

        setContentView(R.layout.activity_main)





//        webView = findViewById(R.id.webView) as WebView
//
//        val settings = webView!!.getSettings()
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
//        settings.setAppCacheEnabled(true)
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE)
//        settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
//        settings.setJavaScriptEnabled(true)
//        settings.setJavaScriptCanOpenWindowsAutomatically(true)
//        settings.setUseWideViewPort(false)//关键点
//        settings.setLoadWithOverviewMode(true)
//        webView!!.addJavascriptInterface(GiftCloseListener(this), "listener")
//
//        webView!!.loadUrl("file:////android_asset/index.html")
////        webView!!.loadUrl("https://h.19gofun.com/jf/i/hybrid/gift.html")
//        webView!!.setWebViewClient(MyWebViewClient(webView!!))
//        webView!!.requestFocus()
//
//        gridLayoutManager = GridLayoutManager(this, 3)
////        gridLayoutManager!!.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
////            override fun getSpanSize(position: Int): Int {
////                return if (position == 0) {
////                    4
////                } else {
////                    1
////                }
////            }
////        }
//        recyclerView = findViewById(R.id.recyclerview)
//        recyclerView!!.layoutManager = gridLayoutManager
//        adapter = Adapter()
//        recyclerView!!.adapter = adapter
//
//
//        btnShowEdit.setOnClickListener {
//            flag = !flag
//            linearLayout.visibility = if (flag) View.VISIBLE else View.GONE
//            if (flag) {
//                editView.requestFocus()
//                showSoftInput(editView, this)
//            } else {
//                hideSoftInput(editView, this)
//            }
//        }
//
//        resize.setOnResizeListener(LayoutResizeListener())
//        resize.setClearScreenCallback(this)
//        resize.setOnClickListener(null)
//
//        iv.setOnClickListener {
//            Log.e("sss", "iv")
//        }
//
//        tv.setOnClickListener {
//            Log.e("sss", "tv click === ")
//        }
//
//        tv.viewTreeObserver.addOnGlobalLayoutListener {
//            tv.width
//            tv.height
//            tv.x
//            tv.y
//            Log.e("sss", "width =  " + tv.width)
//            Log.e("sss", "x =  " + tv.x + "\ty = " + tv.y + "\tendX = " + (tv.x + tv.width))
//
//            resize.gotoRoom = Rect(tv.x.toInt(), tv.y.toInt(), tv.x.toInt() + tv.width.toInt(), tv.y.toInt() + tv.height.toInt())
//        }
//
//
//        downLoad("https://developers.google.com")

          Utils.showCountDown(tv)

        Utils.lengthFilter(this, editView, 15, "max length")


//        view = LinearLayout(this)
//        view!!.layoutParams = RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300)
//        view!!.orientation = HORIZONTAL
//        view!!.setBackgroundColor(Color.parseColor("#fcf"))
//
//        myView.addView(view, 0)

        FloatingView.get().add()
        FloatingView.get().listener(object: MagnetViewListener{
            override fun onClick(magnetView: FloatingMagnetView) {
                Toast.makeText(baseContext, "点到我了", Toast.LENGTH_SHORT).show();
            }

            override fun onRemove(magnetView: FloatingMagnetView) {
                Toast.makeText(baseContext, "我没了", Toast.LENGTH_SHORT).show()
            }

        })

        send.setOnClickListener {
//            var params = RelativeLayout.LayoutParams(300, 300)
//            params.topMargin = 100
//            view!!.layoutParams = params
//            view!!.requestLayout()
            val intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnShowEdit.setOnClickListener {
//            var params = RelativeLayout.LayoutParams(500, 500)
//            params.topMargin = 220
//            view!!.layoutParams = params
//            view!!.requestLayout()
        }
    }


    override fun onStart() {
        super.onStart()
        FloatingView.get().attach(this)
    }

    override fun onStop() {
        super.onStop()
        FloatingView.get().detach(this)
    }

    private fun showSoftInput(editText: EditText, context: Context) {
        val inputMeMana = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMeMana.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideSoftInput(view: EditText, context: Context) {
        val inputMeMana = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMeMana.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    class GiftCloseListener constructor(context: Context) {
        @JavascriptInterface
        public fun closeGift() {
            Log.e("sss", "closeGift ====")
        }
        @JavascriptInterface
        public fun hello(message: String) {
            Log.e("sss", "message = " + message)
        }
        @JavascriptInterface
        public fun hello() {
            Log.e("sss", "message = no args")
        }

    }

    class MyWebViewClient(val webView: WebView): WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

    private inner class Adapter : RecyclerView.Adapter<Adapter.Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(LayoutInflater.from(this@MainActivity).inflate(R.layout.item, parent, false))
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
//            holder.imageView.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate))
            holder.bind(list[position], position)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        internal inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
            var imageView: ProgressBar
            var textView: TextView
            init {
                imageView = view.findViewById(R.id.icon)
                textView = view.findViewById(R.id.tv)
            }

            private val handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    val progress = msg.obj as Float
                    if (100 * progress >= 100) {
                        textView.visibility = View.GONE
                        imageView.visibility = View.VISIBLE
                    } else {
                        textView.visibility = View.VISIBLE
                        imageView.visibility = View.GONE
                    }
                    textView.text = "${progress * 100}%"
                }
            }

            var pos = 0

            fun bind(url: String, position: Int) {
                pos = position
                download(baseContext, position, url, object : OnMainCallBack {
                    override fun onProgress(progress: Float) {
                        val message = handler.obtainMessage()
                        message.obj = progress
                        handler.sendMessage(message)
                    }
                })
                download(baseContext, position, mUrl, null)
            }
        }
    }


    interface OnMainCallBack {
        fun onProgress(progress: Float)
    }

    private inner class LayoutResizeListener : ResizeAndScreenClearLayout.OnResizeListener {
        override fun onResize(w: Int, h: Int, oldw: Int, oldh: Int) {

        }

    }

    fun onKeyboardShow(keyboardHeight: Int) {
    }

    private fun downLoad(url: String) {
        val request = Request.Builder().url(url).build()
        val call = getOkHttpClient().newCall(request)
        call.enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("sss", "onerror ===== ")
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.e("sss", "onresponse ===== ")
            }
        })
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(4, TimeUnit.SECONDS)
                .readTimeout(4, TimeUnit.SECONDS)
                .build()
    }



}
