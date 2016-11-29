package com.justwayward.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * Created by zhangqi on 15/12/26.
 * 可以参照这个博客
 * http://blog.csdn.net/nugongahou110/article/details/50409679
 */
public class XFScrollView extends ScrollView{

//    http://www.jianshu.com/p/825f73d8afa1
//    int height = adViewPager.getHeight() - rlSearchBar.getHeight();
//    float fraction = (float) Math.min(Math.max(t, 0), height) / height;
//    int alpha = (int) (fraction * 255);
//    rlSearchBar.getBackground().setAlpha((int) (fraction * 255));
    private OnScrollListener mListener;
    private int downY;
    private int offsetY;

    public interface OnScrollListener{
        void onScroll(int scrollY);
        void onScrollToTop();
        void onScrollToBottom();
    }

    public XFScrollView(Context context) {
        super(context);
    }

    public XFScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XFScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener listener){
        mListener = listener;
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener!=null){
            mListener.onScroll(t);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mListener!=null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //记录按下时的Y坐标
                    downY = (int) ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //记录滑动时的Y坐标
                    int moveY = (int) ev.getY();
                    //计算出一个差值
                    offsetY = moveY - downY;
                    downY = moveY;
                    break;
                case MotionEvent.ACTION_UP:
                    //当手指抬起时判断差值的大小
                    if (offsetY < 0) {//如果小于0，则说明用户手指向上滑动
                        mListener.onScrollToBottom();
                    }else{//如果大于0，则说明用户手指向下滑动
                        mListener.onScrollToTop();
                    }
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
//        if (mListener!=null){
//            mListener.onScroll(scrollY);
//        }
    }
}
