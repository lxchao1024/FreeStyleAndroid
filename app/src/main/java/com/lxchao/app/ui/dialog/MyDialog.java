package com.lxchao.app.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.lxchao.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/8/27
 */
public class MyDialog extends Dialog {

    private View rootView;
    private ViewPager viewPager;
    private List<RecyclerView> data = new ArrayList<>();

    public MyDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout, null);
        setContentView(rootView);

        initView();

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.alpha = 1;
        lp.dimAmount = 0;
        getWindow().setAttributes(lp);
    }

    private void initView() {
        viewPager = rootView.findViewById(R.id.vp);
    }
}
