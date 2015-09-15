package com.yumikoazu.ifengnews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.apkfuns.logutils.LogUtils;
import com.jude.rollviewpager.RollPagerView;
import com.squareup.okhttp.Request;
import com.sw.library.widget.library.UniversalLoadingView;
import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.adapter.NewsListAdapter;
import com.yumikoazu.ifengnews.adapter.NewsRollVpAdapter;
import com.yumikoazu.ifengnews.bean.News;
import com.yumikoazu.ifengnews.common.OnCommonPageSelectedListener;
import com.yumikoazu.ifengnews.constants.ApiConstants;
import com.yumikoazu.ifengnews.net.MyResultCallback;
import com.yumikoazu.ifengnews.net.OkHttpClientManager;
import com.yumikoazu.ifengnews.ui.activity.NewsDocItemActivity;
import com.yumikoazu.ifengnews.ui.activity.NewsGalleryActivity;
import com.yumikoazu.ifengnews.ui.view.RollHintView;
import com.yumikoazu.ifengnews.widgets.LoadMoreListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joker on 2015/9/7.
 */
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener,
        OnCommonPageSelectedListener, SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.ILoadListener {


    //    @Bind(R.id.roll_view_pager)
//    RollPagerView mRollViewPager;
    @Bind(R.id.loadLv)
    LoadMoreListView mLoadLv;
    @Bind(R.id.srfLayout)
    SwipeRefreshLayout mSrfLayout;
    @Bind(R.id.loadingView)
    UniversalLoadingView mLoadingView;

    private List<News.ItemEntity> mNewsList;
    private List<News.ItemEntity> mNewsFocus;

    private int currentPager = 1;
    private String url = ApiConstants.TOUTIAO_URL;

    private NewsListAdapter mNewsListAdapter;
    private NewsRollVpAdapter mNewsRollVpAdapter;

    private RollPagerView mRollViewPager;

    private boolean isFirst = false;


    public static NewsFragment getInstance(String url) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    public static NewsFragment getInstance(List<News.ItemEntity> newsList, List<News.ItemEntity> newsFocus) {
        NewsFragment newsFragment = new NewsFragment();
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        mSrfLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4)
        );

        mSrfLayout.setOnRefreshListener(this);
        mLoadLv.setOnItemClickListener(this);
        mLoadLv.setInterface(this);
        return view;
    }

    private void setUpRollViewPager() {
        mRollViewPager.setAnimationDurtion(1000);
        mNewsRollVpAdapter = new NewsRollVpAdapter(getActivity(), mNewsFocus);
        mRollViewPager.setAdapter(mNewsRollVpAdapter);
        final RollHintView hintView = new RollHintView(getActivity());
        mRollViewPager.setHintView(hintView);
        hintView.setTitleText(mNewsFocus.get(0).getTitle());
        mRollViewPager.getViewPager().setOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (hintView != null) {
                            hintView.setCurrent(position);
                            hintView.setTitleText(mNewsFocus.get(position).getTitle());
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!isFirst) {
            onRefresh();
            isFirst = true;
        }
    }


    private void fetch(String url, int pager) {
        Log.d("lal", url + pager);
        OkHttpClientManager.getAsyn(
                url + pager,
                new MyResultCallback<List<News>>() {

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        mLoadingView.postLoadState(UniversalLoadingView.State.LOADING);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        mLoadingView.postLoadState(UniversalLoadingView.State.GONE);
                    }


                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(List<News> news) {
                        LogUtils.d(news);
                        LogUtils.d(news.get(0).getItem());
                        mNewsList = news.get(0).getItem();
                        mNewsFocus = news.get(1).getItem();
                        updateNewsListAdapter(mNewsList);
                        setUpRollViewPager();
                    }
                }
        );
    }


    private void fetchMore(String url, int pager) {
        Log.d("lal", url + pager);
        OkHttpClientManager.getAsyn(
                url + pager,
                new MyResultCallback<List<News>>() {

                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(List<News> news) {
                        LogUtils.d(news);
                        LogUtils.d(news.get(0).getItem());
                        mNewsList = news.get(0).getItem();
                        loadMoreUpdateNewsListAdapter(mNewsList);
                    }
                }
        );
    }

    private void updateNewsListAdapter(List<News.ItemEntity> items) {
        mRollViewPager = mLoadLv.getRollPagerView();
        mNewsListAdapter = new NewsListAdapter(getActivity(), items);
        mLoadLv.setAdapter(mNewsListAdapter);
    }

    private void loadMoreUpdateNewsListAdapter(List<News.ItemEntity> items) {
        mRollViewPager = mLoadLv.getRollPagerView();
        if (mNewsListAdapter == null) {
            mNewsListAdapter = new NewsListAdapter(getActivity(), items);
            mLoadLv.setAdapter(mNewsListAdapter);
        } else {
            mNewsListAdapter.onDateChange(items);
        }

    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {

                    @Override
                    public void run() {
                        fetchMore(url, ++currentPager);

                        mLoadLv.loadComplete();
                    }
                }, 2000
        );
    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case 0:
                url = ApiConstants.TOUTIAO_URL;
                fetch(url, 1);
                break;

            case 1:
                url = ApiConstants.QICHE_URL;
                fetch(url, 1);
                break;

            case 2:
                url = ApiConstants.FANGCHAN_URL;
                fetch(url, 1);
                break;

            case 3:
                url = ApiConstants.KEJI_URL;
                fetch(url, 1);
                break;

            case 4:
                url = ApiConstants.XINGZUO_URL;
                fetch(url, 1);
                break;

            case 5:
                url = ApiConstants.YULE_URL;
                fetch(url, 1);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int type = mNewsListAdapter.getItemViewType(position - 1);

        Log.d("type", type + "");

        switch (type) {
            case NewsListAdapter.DOC_TYPE:
                Intent intent = new Intent(getActivity(), NewsDocItemActivity.class);
                String url = mNewsList.get(position - 1).getCommentsUrl();
                String title = mNewsList.get(position - 1).getTitle();
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                getActivity().startActivity(intent);
                break;

            case NewsListAdapter.SLIDE_TYPE:
                Intent intentr = new Intent(getActivity(), NewsGalleryActivity.class);
                String galleryId = mNewsList.get(position - 1).getId();
                intentr.putExtra("galleryId",galleryId);
                getActivity().startActivity(intentr);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onRefresh() {
        fetch(url, 1);
        mSrfLayout.setRefreshing(false);
    }


}
