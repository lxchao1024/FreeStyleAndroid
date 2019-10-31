package com.lxchao.mylibrary.window.view

import android.content.Context
import android.view.View
import android.widget.ImageView

import androidx.annotation.DrawableRes

import com.lxchao.mylibrary.R

/**
 * @ClassName EnFloatingView
 * @Description 悬浮窗
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:04
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:04
 */
class EnFloatingView(context: Context) : FloatingMagnetView(context, null) {

    private val mIcon: ImageView

    init {
        View.inflate(context, R.layout.en_floating_view, this)
        mIcon = findViewById(R.id.icon)
    }

    fun setIconImage(@DrawableRes resId: Int) {
        mIcon.setImageResource(resId)
    }

}
