package com.lxchao.myexcel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.lxchao.myexcel.ui.digit.*
import com.lxchao.myexcel.ui.simulation.SimulationCheckedActivity
import com.lxchao.myexcel.ui.simulation.SimulationOpinionActivity
import com.lxchao.myexcel.ui.simulation.SimulationSingleActivity
import com.moxun.tagcloudlib.view.TagCloudView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), TagCloudView.OnTagClickListener {

    private var list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add("数字单选")
        list.add("数字多选")
        list.add("数字判断")
        list.add("数字填空")
        list.add("数字问答")

        list.add("模拟单选")
        list.add("模拟多选")
        list.add("模拟判断")

        list.add("数字单选")
        list.add("数字多选")
        list.add("数字判断")
        list.add("数字填空")
        list.add("数字问答")

        list.add("模拟单选")
        list.add("模拟多选")
        list.add("模拟判断")

        tagView.setOnTagClickListener(this)
        tagView.setAdapter(MainAdapter(list))
    }

    override fun onItemClick(parent: ViewGroup?, view: View?, position: Int) {
        view?.isSelected = !view!!.isSelected
        when (position) {
            0, 8 -> startActivity(Intent(baseContext, SingleActivity::class.java))
            1, 9 -> startActivity(Intent(baseContext, CheckedActivity::class.java))
            2, 10 -> startActivity(Intent(baseContext, OpinionActivity::class.java))
            3, 11 -> startActivity(Intent(baseContext, BlankActivity::class.java))
            4, 12 -> startActivity(Intent(baseContext, QaActivity::class.java))
            5, 13 -> startActivity(Intent(baseContext, SimulationSingleActivity::class.java))
            6, 14 -> startActivity(Intent(baseContext, SimulationCheckedActivity::class.java))
            7, 15 -> startActivity(Intent(baseContext, SimulationOpinionActivity::class.java))
        }
    }
}
