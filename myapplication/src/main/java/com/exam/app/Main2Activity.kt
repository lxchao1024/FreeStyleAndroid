package com.exam.app

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.layout_refresh_footer.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*


class Main2Activity : AppCompatActivity() {

    private var dataList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        for (index in 0..20) {
            dataList.add(index.toString())
        }

        var mAdapter = Adapter(this, dataList)
        var realAdapter = LoadMoreWrapper(mAdapter)

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = realAdapter

        recyclerView.addOnScrollListener(object: EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                realAdapter.setLoadState(realAdapter.LOADING)
                if (dataList.size > 50) {
                    realAdapter.setLoadState(realAdapter.LOADING_END)
                } else {
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            runOnUiThread {
                                for (index in 0..20) {
                                    dataList.add("${index * 10}")
                                }
                                realAdapter.setLoadState(realAdapter.LOADING_COMPLETE)
                            }
                        }
                    }, 1000)
                }
            }

        })
    }

    class Adapter(context: Context, dataList: MutableList<String>): RecyclerView.Adapter<Adapter.MyHolder>() {
        private var context: Context? = null
        private var dataList = mutableListOf<String>()
        private var width = 0
        init {
            this.context = context
            this.dataList = dataList
            width = context.resources.displayMetrics.widthPixels
            Log.e("sss", "width === " + width)

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            return MyHolder(LayoutInflater.from(context).inflate(R.layout.item, null, false))
        }

        override fun getItemCount(): Int = dataList.size

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.itemView.textview.text = position.toString()
            var mItemWh = (width - dp2px(context!!, 60f)) / 3
            holder.itemView.iv.layoutParams = LinearLayout.LayoutParams(mItemWh, mItemWh)
            var params = LinearLayout.LayoutParams(mItemWh, LinearLayout.LayoutParams.WRAP_CONTENT)
            holder.itemView.llContainer.layoutParams = params
            holder.itemView.llContainer.setBackgroundColor(Color.parseColor("#FF0000"))
        }

        class MyHolder(view: View): RecyclerView.ViewHolder(view)



        public fun dp2px(context: Context, dp: Float): Int {
            return (context.resources.displayMetrics.density * dp + 0.5f).toInt()
        }
    }

    class LoadMoreWrapper(adapter: Adapter): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var adapter: Adapter? = null

        // 普通布局
        private val TYPE_ITEM = 1
        // 脚布局
        private val TYPE_FOOTER = 2
        // 当前加载状态，默认为加载完成
        private var loadState = 2
        // 正在加载
        val LOADING = 1
        // 加载完成
        val LOADING_COMPLETE = 2
        // 加载到底
        val LOADING_END = 3

        init {
            this.adapter = adapter
        }

        override fun getItemViewType(position: Int): Int {
            // 最后一个item设置为FooterView
            return if (position + 1 == itemCount) {
                TYPE_FOOTER
            } else {
                TYPE_ITEM
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if (viewType == TYPE_FOOTER) {
                return FooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_refresh_footer, parent, false))
            } else {
                return adapter!!.onCreateViewHolder(parent, viewType)
            }
        }

        override fun getItemCount(): Int {
//            var remain = adapter!!.itemCount % 3
//            if (remain == 0) {
//                return adapter!!.itemCount + 1
//            }
            return adapter!!.itemCount + 1
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is FooterHolder) {
                var footerHolder = holder as FooterHolder
                when (loadState) {
                    LOADING -> {
                        footerHolder.itemView.progress.visibility = View.VISIBLE
                        footerHolder.itemView.loading.visibility = View.VISIBLE
                        footerHolder.itemView.loading.text = "Loading..."
                    }
                    LOADING_COMPLETE -> {
                        footerHolder.itemView.progress.visibility = View.INVISIBLE
                        footerHolder.itemView.loading.visibility = View.INVISIBLE
                        footerHolder.itemView.loading.text = "Complete..."
                    }
                    LOADING_END -> {
                        footerHolder.itemView.progress.visibility = View.GONE
                        footerHolder.itemView.loading.visibility = View.GONE
                        footerHolder.itemView.loading.text = "Ending..."
                    }
                }
            } else {
                adapter!!.onBindViewHolder(holder as Adapter.MyHolder, position)
            }
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            val manager = recyclerView.layoutManager
            if (manager is GridLayoutManager) {
                val gridManager = manager as GridLayoutManager?
                gridManager?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                        return if (getItemViewType(position) == TYPE_FOOTER) gridManager!!.spanCount else 1
                    }
                }
            }
        }

        fun setLoadState(loadState: Int) {
            this.loadState = loadState
            notifyDataSetChanged()
        }

        class FooterHolder(view: View): RecyclerView.ViewHolder(view)
    }

    abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {

        //用来标记是否正在向上滑动
        private var isSlidingUpward = false

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            val manager = recyclerView.layoutManager as GridLayoutManager?
            // 当不滑动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的itemPosition
                val lastItemPosition = manager!!.findLastCompletelyVisibleItemPosition()
                val itemCount = manager.itemCount

                // 判断是否滑动到了最后一个item，并且是向上滑动
                if (lastItemPosition == itemCount - 1) {
                    //加载更多
                    onLoadMore()
                    Log.e("sss", "issksksksk onLoadMore === " + isSlidingUpward)
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
            isSlidingUpward = dy > 0
            Log.e("sss", "pn scrolled == " + isSlidingUpward)
        }

        /**
         * 加载更多回调
         */
        abstract fun onLoadMore()
    }
}
