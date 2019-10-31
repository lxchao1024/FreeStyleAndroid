package com.lxchao.app.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import com.lxchao.app.R
import com.lxchao.app.listener.AppBarStateChangeListener
import kotlinx.android.synthetic.main.aaa.*
import kotlinx.android.synthetic.main.r_item.view.*
import org.json.JSONObject

class Main3Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aaa)



        val content = "{\"code\": 1, \"message\": \"1\"}"

        Log.e("sss", "message = " + (JSONObject(content).getInt("message")))
        print(JSONObject(content).getInt("code"))

        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar.setNavigationOnClickListener {
//            onBackPressed()
//        }

        collapsingToolbarLayout.title = ""

        appBarLayout.addOnOffsetChangedListener(object: AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                if (state == State.COLLAPSED) {
                    //折叠
                    linearLayout.visibility = View.VISIBLE
                    follow.visibility = View.VISIBLE
                } else if (state == State.EXPANDED){
                    //展开
                    linearLayout.visibility = View.GONE
                    follow.visibility = View.GONE
                } else {
                    //中间态
//                    linearLayout.visibility = View.GONE
//                    follow.visibility = View.GONE
                }
            }
        })

//        recyclerView.layoutManager = GridLayoutManager(this, 2)
//        recyclerView.adapter = MyAdapter(this)

//        <androidx.recyclerview.widget.RecyclerView
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:visibility="gone"
//        android:id="@+id/recyclerView" />
    }


    class MyAdapter(context: Context): RecyclerView.Adapter<MyAdapter.MyHolder>() {

        private lateinit var context: Context

        init {
            this.context = context
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            return MyHolder(LayoutInflater.from(context).inflate(R.layout.r_item, null))
        }

        override fun getItemCount(): Int = 20

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.itemView.txt.text = position.toString()
        }


        class MyHolder(view: View): RecyclerView.ViewHolder(view)
    }
}
