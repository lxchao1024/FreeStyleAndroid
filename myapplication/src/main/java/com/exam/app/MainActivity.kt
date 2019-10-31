//package com.exam.app
//
//import android.content.Context
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toolbar
//import androidx.coordinatorlayout.widget.CoordinatorLayout
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.appbar.CollapsingToolbarLayout
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.item.view.*
//import java.nio.file.Files.size
//import android.R
//import android.util.Log
//
//
//class MainActivity : AppCompatActivity() {
//
//    var layout: CoordinatorLayout? = null
//    var toolbar: Toolbar? = null
//    var cal: CollapsingToolbarLayout? = null
//
//    private var dataList = mutableListOf<String>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
////        btnPlay.setOnClickListener {
////            startActivity(Intent(this, PlayActivity::class.java)
////                .putExtra("Theme", "电影2463")
////                .putExtra("Url", "http://223.110.242.130:6610/gitv/live1/G_CCTV-1-HQ/1.m3u8")
////                .putExtra("Title", "电影2463"))
////        }
//
//        for (index in 0..20) {
//            dataList.add(index.toString())
//        }
//
//
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
//        recyclerView.adapter = Adapter(this, dataList)
//    }
//
//
//    class Adapter(context: Context, dataList: MutableList<String>): RecyclerView.Adapter<Adapter.MyHolder>() {
//        private var context: Context? = null
//        private var dataList = mutableListOf<String>()
//        init {
//            this.context = context
//            this.dataList = dataList
//        }
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
//            return MyHolder(LayoutInflater.from(context).inflate(R.layout.item, null, false))
//        }
//
//        override fun getItemCount(): Int = dataList.size + 1
//
//        override fun getItemViewType(position: Int): Int {
//            return if (position == dataList.size) 1 else 0
//        }
//
//        override fun onBindViewHolder(holder: MyHolder, position: Int) {
//            holder.itemView.textview.text = position.toString()
//        }
//
//        class MyHolder(view: View): RecyclerView.ViewHolder(view)
//    }
//}
