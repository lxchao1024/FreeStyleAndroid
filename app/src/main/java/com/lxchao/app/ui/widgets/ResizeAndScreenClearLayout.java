package com.lxchao.app.ui.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import androidx.core.view.ViewCompat;

/**
 * 可监听大小改变、可清屏的自定义View 继承RelativeLayout,
 */
public class ResizeAndScreenClearLayout extends RelativeLayout {
    private static final String TAG = "ResizeLayout";
    private OnResizeListener mListener;

    //	private int childInitialLocationX;
    private boolean hasFlingRun = false;
    private boolean isScrollFisrtIn = true;
    private int direction;
    private boolean canScroll = false;

    private int screenW;
    private GestureDetector gestureDetector;

    private ClearScreenCallback mClearScreenCallback;

    private Handler handler = new Handler();
    private Scroller mScroller;
    private SparseArray<Rect> roomRects = new SparseArray<>();
    private int roomType = 1;

    private Rect gotoRoom;

    public ResizeAndScreenClearLayout(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenW = wm.getDefaultDisplay().getWidth();
        init();
    }

    public ResizeAndScreenClearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mScroller = new Scroller(getContext());

        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                /*LogUtils.d(TAG, "onScroll() [" +
                        "isScrollFisrtIn=" + isScrollFisrtIn + ", " +
						"direction=" + direction + ", " +
						"canScroll=" + canScroll + ", " +
						"distanceX=" + distanceX + ", " +
						"distanceY=" + distanceY + ", " +
						"mScroller.isFinished=" + mScroller.isFinished() + ", " +
						"]");*/
                if (isScrollFisrtIn) {
                    isScrollFisrtIn = false;
                    if (Math.abs(distanceX) >= Math.abs(distanceY)) {
                        direction = 0;
                        if (getScrollX() == 0 && distanceX < 0) {
//						if (Math.abs(getScrollX()) < 50 && distanceX < 0) {
                            // 在初始位置，可以向右滑动
                            canScroll = true;
                            scrollBy((int) distanceX, 0);
                        }
                        if (getScrollX() == -screenW && distanceX > 0) {
//						if (Math.abs(screenW + getScrollX()) < 50 && distanceX > 0) {
                            // 滑出界面了，可以向左滑动
                            canScroll = true;
                            scrollBy((int) distanceX, 0);
                        }
                    } else {
                        direction = 1;
                    }
                } else {
                    if (direction == 0) {
                        if (canScroll) {
                            // 执行水平方向的滚动
                            scrollBy((int) distanceX, 0);
                        }
                    } else {
                        // 执行垂直方向的滚动
                    }
                }
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                /*LogUtils.d(TAG, "onFling() [" +
						"velocityX=" + velocityX + ", " +
						"velocityY=" + velocityY + ", " +
						"mScroller.isFinished=" + mScroller.isFinished() + ", " +
						"]");*/
                hasFlingRun = true;
                fling(velocityX);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
				/*LogUtils.d(TAG, "onDown() [" +
						"mScroller.isFinished=" + mScroller.isFinished() + ", " +
						"mScrollX=" + getScrollX() + ", " +
						"]");*/
                if (!mScroller.isFinished()) {
                    scrollTo(mScroller.getFinalX(), 0);
                }
                mScroller.forceFinished(true);
                if (0 == roomType) {
                    return false;
                }
                if (null != roomRects && roomRects.size() != 0) {
                    Log.e("sss", "on down if");
                    for (int i = 0; i < roomRects.size(); i++) {
                        Rect rect = roomRects.valueAt(i);
                        if (rect == null)
                            continue;
                        boolean b = !rect.contains((int) e.getRawX(), (int) e.getRawY());
                        if (!b) {
                            return false;
                        }
                    }
                }

                if (null != gotoRoom) {
                    return gotoRoom.contains((int) e.getX(), (int) e.getY());
                }

                return true;
            }
        });
    }

    public void setOnResizeListener(OnResizeListener l) {
        mListener = l;
    }

    public void setClearScreenCallback(ClearScreenCallback clearScreenCallback) {
        this.mClearScreenCallback = clearScreenCallback;
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mListener != null) {
                    mListener.onResize(w, h, oldw, oldh);
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("sss", "resize view on touch === ");
        boolean result = gestureDetector.onTouchEvent(event);
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (!hasFlingRun) {
                    scrollToDestination();
                }
                hasFlingRun = false;
                isScrollFisrtIn = true;
                canScroll = false;
                break;
        }
        return result;
    }

    private void scrollToDestination() {
        if (!canScroll) {
            return;
        }
        mScroller.forceFinished(true);
        if (-getScrollX() >= screenW / 2) {
            // 超过一半，向右滑
//			scrollTo(-screenW, 0);
            if (mClearScreenCallback != null) {
                mClearScreenCallback.onClearScreen(true);
            }
            mScroller.startScroll(getScrollX(), 0, -screenW - getScrollX(), 0);
        } else {
            // 向左滑
//			scrollTo(0, 0);
            if (mClearScreenCallback != null) {
                mClearScreenCallback.onClearScreen(false);
            }
            mScroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void fling(float velocityX) {
        if (!canScroll) {
            return;
        }
        mScroller.forceFinished(true);
        if (velocityX > 0) {
            // 正向，向右滑
            if (mClearScreenCallback != null) {
                mClearScreenCallback.onClearScreen(true);
            }
            mScroller.startScroll(getScrollX(), 0, -screenW - getScrollX(), 0);
        } else {
            // 向左滑
            if (mClearScreenCallback != null) {
                mClearScreenCallback.onClearScreen(false);
            }
            mScroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void resetToPosition() {
        if (mClearScreenCallback != null) {
            mClearScreenCallback.onClearScreen(false);
        }
        scrollTo(0, 0);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
			/*LogUtils.d(TAG, "computeScroll() canScroll [" +
					"mScroller.curX=" + mScroller.getCurrX() + ", " +
					"mScroller.curY=" + mScroller.getCurrY() + ", " +
					"]");*/
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//			LogUtils.d(TAG, "computeScroll() mScrollX = " + getScrollX());
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void addRoomRect(int tag, Rect rect) {
        roomRects.put(tag, rect);
    }

    public void removeRoomRect(int tag) {
        roomRects.remove(tag);
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public void clearRoomRect() {
        roomRects.clear();
    }

    //回到非清屏状态
    public void releaseScreen() {
        // 向左滑
        if (mClearScreenCallback != null) {
            mClearScreenCallback.onClearScreen(false);
        }
        scrollTo(0, 0);
    }

    interface ClearScreenCallback {
        void onClearScreen(boolean isCleared);
    }

    public interface OnResizeListener {
        void onResize(int w, int h, int oldw, int oldh);
    }

    public void setGotoRoom(Rect gotoRoom) {
        this.gotoRoom = gotoRoom;
    }

    public Rect getGotoRoom() {
        return gotoRoom;
    }
}
