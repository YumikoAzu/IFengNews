package com.yumikoazu.ifengnews.widgets;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.jude.rollviewpager.RollPagerView;
import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.ui.view.CircularProgress;

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    View footer;// 底部布局；
    int totalItemCount;// 总数量；
    int lastVisibleItem;// 最后一个可见的item；
    boolean isLoading;// 正在加载；
    ILoadListener iLoadListener;
    private CircularProgress mMaterialCircleView;

    public RollPagerView getRollPagerView() {
        return mRollPagerView;
    }

    private RollPagerView mRollPagerView;

    public LoadMoreListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    /**
     * 添加底部加载提示布局到listview
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mRollPagerView = (RollPagerView) inflater.inflate(R.layout.news_list_header, this,false);
        this.addHeaderView(mRollPagerView);
        footer = inflater.inflate(R.layout.load_more_footer,  this,false);
        footer.findViewById(R.id.load_layout).setVisibility(View.GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibleItem
                && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(
                        View.VISIBLE);
                // 加载更多
                iLoadListener.onLoad();
            }
        }
    }
    /**
     * 加载完毕
     */
    public void loadComplete(){
        isLoading = false;
        footer.findViewById(R.id.load_layout).setVisibility(
                View.GONE);
    }

    public void setInterface(ILoadListener iLoadListener){
        this.iLoadListener = iLoadListener;
    }
    //加载更多数据的回调接口
    public interface ILoadListener{
        public void onLoad();
    }
}