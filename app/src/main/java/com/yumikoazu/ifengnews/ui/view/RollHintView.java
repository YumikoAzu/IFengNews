package com.yumikoazu.ifengnews.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.rollviewpager.HintView;

/**
 * Created by joker on 2015/9/7.
 */
public class RollHintView extends RelativeLayout implements HintView {

    private TextView mTitleTv;
    private TextView mNumTv;
    private int length;
    private Context mContext;
    private LayoutParams mTitleParams, mNumParams;

    public RollHintView(Context context) {
        super(context);
    }

    public RollHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public void initView(int length, int gravity) {
        mTitleTv = new TextView(getContext());
        mNumTv = new TextView(getContext());

        mTitleTv.setTextColor(Color.WHITE);
        mNumTv.setTextColor(Color.WHITE);
        switch (gravity) {
            case 0:
                setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
            case 1:
                setGravity(Gravity.CENTER);
                break;
            case 2:
                setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                break;
        }

        this.length = length;
        setBackgroundColor(0x30000000);
        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        mTitleParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        mTitleParams.setMargins(10,0,0,0);
        mNumParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mNumParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        mNumParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        mNumParams.setMargins(0, 0, 10, 0);
        addView(mTitleTv, mTitleParams);
        addView(mNumTv, mNumParams);
        setCurrent(0);
    }

    @Override
    public void setCurrent(int current) {
        mNumTv.setText("");
        mNumTv.setText(current + 1 + "/" + length);
    }

    public void setTitleText(String titleText) {
        mTitleTv.setText("");
        mTitleTv.setText(titleText);
    }

}
