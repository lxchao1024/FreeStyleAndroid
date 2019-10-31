package com.exam.app.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

public class CallbkRecyclerView extends RecyclerView {
    private OnReachBottomListener onReachBottomListener;
    private boolean isInTheBottom = false;
    private int reachBottomRow = 1;
    private List<OnScrollListener> mScrollListeners;
    public CallbkRecyclerView(Context context) {
        super(context);
    }
    public CallbkRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CallbkRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void onScrolled(int dx, int dy) {
        if (onReachBottomListener != null) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager == null) { //it maybe unnecessary
                throw new RuntimeException("LayoutManager is null,Please check it!");
            }
            Adapter adapter = getAdapter();
            if (adapter == null) { //it maybe unnecessary
                throw new RuntimeException("Adapter is null,Please check it!");
            }
            boolean isReachBottom = false;
            //is GridLayoutManager
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                int rowCount = adapter.getItemCount() / gridLayoutManager.getSpanCount();
                int lastVisibleRowPosition = gridLayoutManager.findLastVisibleItemPosition() / gridLayoutManager.getSpanCount();
                isReachBottom = (lastVisibleRowPosition >= rowCount - reachBottomRow);
            }
            //is LinearLayoutManager
            else if (layoutManager instanceof LinearLayoutManager) {
                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                int rowCount = adapter.getItemCount();
                if (reachBottomRow > rowCount)
                    reachBottomRow = 1;
                isReachBottom = (lastVisibleItemPosition >= rowCount - reachBottomRow);
            }
            //is StaggeredGridLayoutManager
            else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int spanCount = staggeredGridLayoutManager.getSpanCount();
                int[] into = new int[spanCount];
                int[] eachSpanListVisibleItemPosition = staggeredGridLayoutManager.findLastVisibleItemPositions(into);
                for (int i = 0; i < spanCount; i++) {
                    if (eachSpanListVisibleItemPosition[i] > adapter.getItemCount() - reachBottomRow * spanCount) {
                        isReachBottom = true;
                        break;
                    }
                }
            }
            if (!isReachBottom) {
                isInTheBottom = false;
            } else if (!isInTheBottom) {
                onReachBottomListener.onReachBottom();
                isInTheBottom = true;
                Log.d("RBCallbkRecyclerView", "onReachBottom");
            }
        }
    }
    public void setReachBottomRow(int reachBottomRow) {
        if (reachBottomRow < 1)
            reachBottomRow = 1;
        this.reachBottomRow = reachBottomRow;
    }
    public interface OnReachBottomListener {
        void onReachBottom();
    }

    public void setOnReachBottomListener(OnReachBottomListener onReachBottomListener) {
        this.onReachBottomListener = onReachBottomListener;
    }
//public  void addOnScrollListener(OnScrollListener listener){
//    if(mScrollListeners==null){
//        mScrollListeners=new ArrayList<OnScrollListener>();
//    }
//    mScrollListeners.add(listener);
//}
}
