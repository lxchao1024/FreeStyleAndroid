package com.exam.app

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.app.utils.DividerItemDecoration
import com.exam.app.utils.FullyGridLayoutManager
import kotlinx.android.synthetic.main.aaa.*
import kotlinx.android.synthetic.main.aaa.recyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Main3Activity : AppCompatActivity() {


    private var dataList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aaa)

        for (index in 0..9) {
            dataList.add(index.toString())
        }

        Log.e("sss", "size ---- " + dataList.size)

        var mAdapter = Main2Activity.Adapter(this, dataList)
        var realAdapter = Main2Activity.LoadMoreWrapper(mAdapter)

        recyclerView.layoutManager = FullyGridLayoutManager(this, 3)

        recyclerView.adapter = realAdapter

        recyclerView.addOnScrollListener(object: Main2Activity.EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                realAdapter.setLoadState(realAdapter.LOADING)
                if (dataList.size > 50) {
                    realAdapter.setLoadState(realAdapter.LOADING_END)
                } else {
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            runOnUiThread {
                                for (index in 0..9) {
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


    class SpacesItemDecoration(space: Int): RecyclerView.ItemDecoration() {
        private var space: Int

        init {
            this.space = space
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
            outRect.top = if (parent.getChildLayoutPosition(view) == 0) space else 0
        }
    }
}
