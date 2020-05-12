package com.lxchao.myexcel.ui.digit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lxchao.myexcel.ExcelReader
import com.lxchao.myexcel.R
import com.lxchao.myexcel.SpUtils
import com.lxchao.myexcel.digit.WenDa
import kotlinx.android.synthetic.main.activity_blank.*

class QaActivity : AppCompatActivity() {
    private var data = mutableListOf<WenDa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        data = ExcelReader.readQa("${externalCacheDir!!.path}/digit/wenda.xls")
        viewPager.adapter = QaPagerAdapter(baseContext, data)
        viewPager.currentItem = SpUtils.get(SpUtils.KEY_DIGIT_QA)
    }
}
