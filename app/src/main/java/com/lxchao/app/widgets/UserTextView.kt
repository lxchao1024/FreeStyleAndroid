package com.lxchao.app.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 *
 * @author lixiangchao
 * @date 2019/11/11
 * @version 1.0.0
 */
class UserTextView(context: Context, attrs: AttributeSet): TextView(context, attrs) {
    init {
        val typeface = Typeface.createFromAsset(resources.assets, "iconfont.ttf")
        setTypeface(typeface)
    }
}