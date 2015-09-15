package com.yumikoazu.ifengnews.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.yumikoazu.ifengnews.utils.ScreenTools;

/**
 * Created by joker on 2015/9/10.
 */
public class NewsSlideLayout extends ViewGroup {

    /**
     * 图片之间的间隔
     */
    private int gap = 5;
    private int totalWidth;

    public NewsSlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ScreenTools screenTools = ScreenTools.instance(getContext());
        totalWidth = screenTools.getScreenWidth() - screenTools.dip2px(24);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cCount = getChildCount();
        //设置子view的位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        Log.d("hh1",left+"");
        int childWidth = (totalWidth - gap * (3 - 1)) / 3;
        Log.d("hh2",childWidth+"");
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childHeight = child.getHeight();
            Log.d("hh",childHeight+"");
            int lc = (childWidth + gap) * i + getPaddingLeft();
            int tc = top + lp.topMargin;
            int rc = lc + childWidth;
            int bc = tc + childHeight;

            //为子view进行布局
            child.layout(lc, tc, rc, bc);


        }
    }


    /**
     * 与当前ViewGroup对应的LayoutParams
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
