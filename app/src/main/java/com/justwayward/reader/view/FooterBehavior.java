package com.justwayward.reader.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.justwayward.reader.R;

/**
 * @auther jjr
 * @date 创建时间： 2016/11/29 14:00
 * @Description
 * 参照博客:http://blog.csdn.net/tiankong1206/article/details/48394393
 */

public class FooterBehavior extends CoordinatorLayout.Behavior {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private Animation mShowAnim;
    private Animation mDismissAnim;
    private int sinceDirectionChange;

    public FooterBehavior() {

    }

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        LinearLayout linearLayout= (LinearLayout) child;
        Log.i("layoutDependsOn","layoutDependsOn");
        return super.layoutDependsOn(parent, child, dependency);
    }

    //1.判断滑动的方向 我们需要垂直滑动
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //2.根据滑动的距离显示和隐藏footer view
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
//            child.animate().cancel();
            child.clearAnimation();
            sinceDirectionChange = 0;
        }
        Log.i("onNestedPreScroll","dy="+dy);
        sinceDirectionChange += dy;
        if (sinceDirectionChange > child.getHeight() && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (sinceDirectionChange < 0 && child.getVisibility() == View.GONE) {
            show(child);
        }
    }

    private void hide(final View view) {
        if(view.isShown()){
            if(mDismissAnim==null){
                mDismissAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.bottom_dismiss);
                mDismissAnim.setInterpolator(INTERPOLATOR);
            }
            view.clearAnimation();
            view.startAnimation(mDismissAnim);
            view.setVisibility(View.GONE);
        }
//        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).setInterpolator(INTERPOLATOR).setDuration(200);
//        animator.setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                view.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//                show(view);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        animator.start();
    }

    private void show(final View view) {
        if(!view.isShown()){
            if(mShowAnim==null){
                mShowAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.bottom_show);
                mShowAnim.setInterpolator(INTERPOLATOR);
            }
            view.clearAnimation();
            view.startAnimation(mShowAnim);
            view.setVisibility(View.VISIBLE);
        }
//        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(1000);
//        animator.setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                view.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//                hide(view);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        animator.start();
    }
}
