package com.lxchao.myexcel.ui.digit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lxchao.myexcel.ExcelReader
import com.lxchao.myexcel.R
import com.lxchao.myexcel.SpUtils
import com.lxchao.myexcel.digit.PanDuan
import kotlinx.android.synthetic.main.activity_blank.*

class OpinionActivity : AppCompatActivity() {
    private var data = mutableListOf<PanDuan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        data = ExcelReader.readOpinion("${externalCacheDir!!.path}/digit/panduan.xls")
        viewPager.adapter = OpinionPagerAdapter(baseContext, data)
        viewPager.currentItem = SpUtils.get(SpUtils.KEY_DIGIT_OPINION)
    }
}
