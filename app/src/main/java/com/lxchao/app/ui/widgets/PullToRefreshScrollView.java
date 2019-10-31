package com.lxchao.app.ui.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.TextView;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/10/12
 */
public class PullToRefreshScrollView extends ViewGroup {

    private TextView fooder_layout;
    private TextView top_layout;

    private int desireWidth, desireHeight;
    private VelocityTracker velocityTracker;
    private int mPointerId;
    private float x, y;
    private OverScroller mScroller;
    private int maxFlingVelocity, minFlingVelocity;
    private int mTouchSlop;
    protected Boolean isMove = false;
    protected float downX = 0, downY = 0;
    private int top_hight = 0;
    private int scrollYButtom = 0;
    private int nScrollYButtom = 0;

    private int pullDownMin = 0;
    private Boolean isEnablePullDown = true;

    private Boolean isFirst=true;

    public void setEnablePullDown(Boolean isEnablePullDown) {
        this.isEnablePullDown = isEnablePullDown;
    }

    public PullToRefreshScrollView(Context context) {
        this(context, null);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        mScroller = new OverScroller(getContext());
        maxFlingVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
        minFlingVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        fooder_layout = new TextView(getContext());
        fooder_layout.setTextSize(16f);
        fooder_layout.setText("FooderLayout");
        fooder_layout.setGravity(Gravity.CENTER);

        top_layout = new TextView(getContext());
        top_layout.setText("TopLayout");
        top_layout.setTextSize(16f);
        top_layout.setGravity(Gravity.CENTER);

        if (isEnablePullDown) {

        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
