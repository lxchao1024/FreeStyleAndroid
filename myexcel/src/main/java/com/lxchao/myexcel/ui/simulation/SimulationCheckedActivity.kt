package com.lxchao.myexcel.ui.simulation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lxchao.myexcel.ExcelReader
import com.lxchao.myexcel.R
import com.lxchao.myexcel.SpUtils
import com.lxchao.myexcel.digit.DuoXuan
import com.lxchao.myexcel.ui.digit.SimulationCheckedPagerAdapter
import kotlinx.android.synthetic.main.activity_blank.*

class SimulationCheckedActivity : AppCompatActivity() {

    private var data = mutableListOf<DuoXuan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        data = ExcelReader.readChecked("${externalCacheDir!!.path}/simulation/duoxuan.xls")
        viewPager.adapter = SimulationCheckedPagerAdapter(baseContext, data)
        viewPager.currentItem = SpUtils.get(SpUtils.KEY_SIMULATION_CHECKED)
    }
}
