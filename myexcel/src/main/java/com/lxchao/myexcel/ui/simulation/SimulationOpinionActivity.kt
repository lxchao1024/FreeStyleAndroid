package com.lxchao.myexcel.ui.simulation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lxchao.myexcel.ExcelReader
import com.lxchao.myexcel.R
import com.lxchao.myexcel.SpUtils
import com.lxchao.myexcel.digit.PanDuan
import com.lxchao.myexcel.ui.digit.SimulationOpinionPagerAdapter
import kotlinx.android.synthetic.main.activity_blank.*

class SimulationOpinionActivity : AppCompatActivity() {
    private var data = mutableListOf<PanDuan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        data = ExcelReader.readOpinion("${externalCacheDir!!.path}/simulation/panduan.xls")
        viewPager.adapter = SimulationOpinionPagerAdapter(baseContext, data)
        viewPager.currentItem = SpUtils.get(SpUtils.KEY_SIMULATION_OPINION)
    }
}
