package com.lxchao.myexcel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/19
 */
public class MainAdapter extends TagsAdapter {

    private List<String> mList;

    public MainAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        TextView tvTag = (TextView) View.inflate(context,R.layout.item_tag,null);
        tvTag.setText(getItem(position));
        return tvTag;
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return 1;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {

    }
}
