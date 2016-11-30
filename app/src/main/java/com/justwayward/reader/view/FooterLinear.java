package com.justwayward.reader.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.justwayward.reader.R;

/**
 * @auther jjr
 * @date 创建时间： 2016/11/30 13:44
 * @Description
 */
@CoordinatorLayout.DefaultBehavior(FooterBehavior.class)
public class FooterLinear extends LinearLayout {

    public FooterLinear(Context context) {
        this(context,null);
    }

    public FooterLinear(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FooterLinear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
//        View.inflate(context,R.layout.bottom_layout,this);
        LayoutInflater.from(context).inflate(R.layout.bottom_layout, this);
    }
}
