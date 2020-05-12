package com.lxchao.app.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;


/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/11
 */
@SuppressLint("AppCompatCustomView")
public class User extends TextView {
    public User(Context context) {
        super(context);
        init(context);
    }

    public User(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public User(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface typeFace = Typeface
                .createFromAsset(context.getAssets(), "iconfont.ttf");
        this.setTypeface(typeFace);
    }
}
