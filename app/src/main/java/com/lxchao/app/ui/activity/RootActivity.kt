package com.lxchao.app.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lxchao.app.R
import kotlinx.android.synthetic.main.activity_root.*

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        excel.setOnClickListener{ startActivity(Intent(baseContext, GiftActivity::class.java)) }
        grid.setOnClickListener{ startActivity(Intent(baseContext, GridActivity::class.java)) }
        mainA.setOnClickListener{ startActivity(Intent(baseContext, MainActivity::class.java)) }
        main3.setOnClickListener{ startActivity(Intent(baseContext, Main3Activity::class.java)) }
        main4.setOnClickListener{ startActivity(Intent(baseContext, Main4Activity::class.java)) }
        main5.setOnClickListener{ startActivity(Intent(baseContext, Main5Activity::class.java)) }
        gridRecy.setOnClickListener{ startActivity(Intent(baseContext, GridRecyclerViewActivity::class.java)) }
    }
}
