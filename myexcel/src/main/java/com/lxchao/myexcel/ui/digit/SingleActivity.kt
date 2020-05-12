package com.lxchao.myexcel.ui.digit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lxchao.myexcel.ExcelReader
import com.lxchao.myexcel.R
import com.lxchao.myexcel.SpUtils
import com.lxchao.myexcel.digit.DanXuan
import kotlinx.android.synthetic.main.activity_blank.*

class SingleActivity : AppCompatActivity() {

    private var data = mutableListOf<DanXuan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        data = ExcelReader.readSingle("${externalCacheDir!!.path}/digit/danxuan.xls")

        viewPager.adapter = SinglePagerAdapter(baseContext, data)
        viewPager.currentItem = SpUtils.get(SpUtils.KEY_DIGIT_SINGLE)
    }
}
