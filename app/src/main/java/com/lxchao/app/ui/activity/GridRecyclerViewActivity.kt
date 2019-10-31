package com.lxchao.app.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lxchao.app.R
import kotlinx.android.synthetic.main.activity_grid_recycler_view.*
import kotlinx.android.synthetic.main.grid_item.view.*
import android.graphics.Rect

/**
 * 单纯的测试RecyclerView的网格布局和间隔设置
 */
class GridRecyclerViewActivity : AppCompatActivity() {

    var list: MutableList<String> = ArrayList()
    var adapter: GridAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_recycler_view)

        for (i in 0..20) {
            list.add((i * 10).toString())
        }
        Log.e("sss", "size == " + list.size)
        adapter = GridAdapter(list, this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.addItemDecoration(SpaceItemDecoration(10))
        recyclerView.adapter = adapter
    }


    class GridAdapter(private val list: MutableList<String>, private val context: Context): RecyclerView.Adapter<GridAdapter.Holder>() {
        var width = 0
        init {
            width = context.resources.displayMetrics.widthPixels / 3 - 10
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(LayoutInflater.from(context).inflate(R.layout.grid_item, null))
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: Holder, position: Int) {

            holder.itemView.imageView.layoutParams = LinearLayout.LayoutParams(width, width)

        }

        class Holder(view: View): RecyclerView.ViewHolder(view)
    }

    inner class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space
//            outRect.bottom = space
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % 3 == 0) {
                outRect.left = 0
            }
        }
    }
}
