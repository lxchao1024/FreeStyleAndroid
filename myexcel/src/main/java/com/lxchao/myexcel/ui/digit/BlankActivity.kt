package com.lxchao.myexcel.ui.digit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lxchao.myexcel.ExcelReader
import com.lxchao.myexcel.R
import com.lxchao.myexcel.SpUtils
import com.lxchao.myexcel.digit.TianKong
import kotlinx.android.synthetic.main.activity_blank.*

class BlankActivity : AppCompatActivity() {

    private var data = mutableListOf<TianKong>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        data = ExcelReader.readBlank("${externalCacheDir!!.path}/digit/tiankong.xls")
        viewPager.adapter = CustomPagerAdapter(baseContext, data)
        viewPager.currentItem = SpUtils.get(SpUtils.KEY_DIGIT_BLANK)
    }
}
