package com.lxchao.app.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.lxchao.app.ui.adapter.ImageGridViewAdapter;
import com.lxchao.app.R;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageGridViewAdapter adapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        data.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1566182818&di=7bb5eb20d12069ea9da1655a4aee4eec&src=http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566192905005&di=a39c9f5963b786933b59c8360cc49a49&imgtype=0&src=http%3A%2F%2Fimg.hexun.com%2F2011-03-04%2F127722760.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566192905003&di=98fbda79600120603c895a1da5b49fe6&imgtype=0&src=http%3A%2F%2Fc4.mtimg.net%2F4%2Fpvpic%2F131103%2F17%2Fq22AHnf_o.jpg");
        data.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1566182818&di=7bb5eb20d12069ea9da1655a4aee4eec&src=http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566192905005&di=a39c9f5963b786933b59c8360cc49a49&imgtype=0&src=http%3A%2F%2Fimg.hexun.com%2F2011-03-04%2F127722760.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566192905003&di=98fbda79600120603c895a1da5b49fe6&imgtype=0&src=http%3A%2F%2Fc4.mtimg.net%2F4%2Fpvpic%2F131103%2F17%2Fq22AHnf_o.jpg");
        data.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1566182818&di=7bb5eb20d12069ea9da1655a4aee4eec&src=http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566192905005&di=a39c9f5963b786933b59c8360cc49a49&imgtype=0&src=http%3A%2F%2Fimg.hexun.com%2F2011-03-04%2F127722760.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1566192905003&di=98fbda79600120603c895a1da5b49fe6&imgtype=0&src=http%3A%2F%2Fc4.mtimg.net%2F4%2Fpvpic%2F131103%2F17%2Fq22AHnf_o.jpg");
        data.add("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1566182818&di=7bb5eb20d12069ea9da1655a4aee4eec&src=http://img1.cache.netease.com/catchpic/4/47/47E5E3CA5A91B67C4D76CF2F01970A3D.jpg");
        gridView = findViewById(R.id.grid_view);
        adapter = new ImageGridViewAdapter(this, gridView, data);
        gridView.setAdapter(adapter);
    }
}
