package com.lxchao.app.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.lxchao.app.utils.ImageDownLoader;
import com.lxchao.app.R;

import java.util.List;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date {2019/8/19}
 */
public class ImageGridViewAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    /** 数据源 */
    private List<String> data;
    /** 图片下载类 */
    private ImageDownLoader loader;
    /** 判定是否第一次加载 */
    private boolean isFirstEnter = true;
    /** 第一张可见Item下标 */
    private int firstVisibleItem;
    /** 每屏Item可见数 */
    private int visibleItemCount;
    /** GridView实例 */
    private GridView gridView;
    private Context context;


    public ImageGridViewAdapter(Context context, GridView gridView, List<String> data) {
        this.context = context;
        this.gridView = gridView;
        this.data = data;
        loader = new ImageDownLoader(context);
        this.gridView.setOnScrollListener(this);
    }


    @Override
    public int getCount() {
        return null == data ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        String url = data.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.photo_item, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
        // 设置Tag，保证异步加载图片不乱序
        imageView.setTag(url);
        setImageView(imageView, url);
        return convertView;
    }

    private void setImageView(ImageView imageView, String url) {
        Bitmap bitmap = loader.getBitmapCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // 当停止滚动时，加载图片
        if (scrollState == SCROLL_STATE_IDLE) {
            loadImage(firstVisibleItem, visibleItemCount);
            Log.e("sss", "adapter onScrollStateChanged if ==== ");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        Log.e("sss", "adapter onScroll ==== ");
        this.firstVisibleItem = firstVisibleItem;
        this.visibleItemCount = visibleItemCount;
        if (isFirstEnter && visibleItemCount > 0) {
            Log.e("sss", "adapter onScroll if if ==== ");
            loadImage(firstVisibleItem, visibleItemCount);
            isFirstEnter = false;
        }
    }

    /**
     * 加载图片，若缓存中没有，则根据url下载
     *
     * @param firstVisibleItem
     * @param visibleItemCount
     */
    private void loadImage(int firstVisibleItem, int visibleItemCount) {
        Bitmap bitmap = null;
        for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
            String url = data.get(i);
            final ImageView imageView = (ImageView) gridView
                    .findViewWithTag(url);
            bitmap = loader.getBitmapCache(url);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                // 防止滚动时多次下载
                if (loader.getTaskCollection().containsKey(url)) {
                    continue;
                }
                imageView.setImageDrawable(context.getResources().getDrawable(
                        R.mipmap.ic_launcher));
                loader.loadImage(url, imageView.getWidth(),
                        imageView.getHeight(),
                        new ImageDownLoader.AsyncImageLoaderListener() {

                            @Override
                            public void onImageLoader(Bitmap bitmap) {
                                if (imageView != null && bitmap != null) {
                                    imageView.setImageBitmap(bitmap);
                                }
                            }

                        });
            }
        }
    }

    /**
     * 取消下载任务
     */
    public void cancelTasks() {
        loader.cancelTasks();
    }
}
