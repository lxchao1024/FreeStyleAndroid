package com.lxchao.myexcel.ui.digit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.lxchao.myexcel.R;
import com.lxchao.myexcel.SpUtils;
import com.lxchao.myexcel.digit.TianKong;

import java.util.List;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/18
 */
public class CustomPagerAdapter extends PagerAdapter {
    //上下文
    private Context mContext;
    //数据
    private List<TianKong> mData;

    /**
     * 构造函数
     * 初始化上下文和数据
     *
     * @param context
     * @param list
     */
    public CustomPagerAdapter(Context context, List<TianKong> list) {
        mContext = context;
        mData = list;
    }

    /**
     * 返回要滑动的VIew的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * 1.将当前视图添加到container中
     * 2.返回当前View
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.fragment_item_blank, null);
        TextView title = view.findViewById(R.id.tv_item_title);
        TextView tv = view.findViewById(R.id.tv_item_content);
        TextView answer = view.findViewById(R.id.tv_item_answer);
        TianKong data = mData.get(position);
        title.setText("题目名称： " + data.get题目名称());
        tv.setText("问题：\n" + data.get题目内容());
        answer.setText("答案：" + data.get答案());

        TextView pos = view.findViewById(R.id.tv_item_pos);
        pos.setText("当前进度： " + (position + 1)  + " / " + mData.size());

        SpUtils.put(SpUtils.KEY_DIGIT_BLANK, position);

        container.addView(view);
        return view;
    }

    /**
     * 从当前container中删除指定位置（position）的View
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View) object);
    }

    /**
     * 确定页视图是否与特定键对象关联
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}