package com.yumikoazu.ifengnews.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import com.apkfuns.logutils.LogUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.okhttp.Request;
import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.adapter.NewsPagerAdapter;
import com.yumikoazu.ifengnews.bean.News;
import com.yumikoazu.ifengnews.constants.ApiConstants;
import com.yumikoazu.ifengnews.constants.NewsConstants;
import com.yumikoazu.ifengnews.net.MyResultCallback;
import com.yumikoazu.ifengnews.net.OkHttpClientManager;
import com.yumikoazu.ifengnews.ui.fragment.NewsFragment;
import com.yumikoazu.ifengnews.widgets.XViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab_smart)
    SmartTabLayout mtabSmart;
    @Bind(R.id.news_pager)
    XViewPager mNewsPager;


    private List<String> tabString = Arrays.asList(NewsConstants.tabs);

    private NewsPagerAdapter mNewsPagerAdapter;

    private List<News.ItemEntity> mNewsList;
    private List<News.ItemEntity> mNewsFocus;

    private String url = ApiConstants.TOUTIAO_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mNewsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), tabString);
        mNewsPager.setAdapter(mNewsPagerAdapter);

        mtabSmart.setViewPager(mNewsPager);
        mtabSmart.setOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        Log.d("in", position + "");
                        NewsFragment newsFragment = (NewsFragment) mNewsPager.getAdapter().instantiateItem(mNewsPager, position);
                        newsFragment.onPageSelected(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );
        initWindow();
//        OkHttpClientManager.getAsyn(
//               "http://api.iclient.ifeng.com/ipadtestdoc?aid=101257217",
//                new MyResultCallback<NewsSlideItem>() {
//
//
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(NewsSlideItem newsSlideItem) {
//                        LogUtils.d(newsSlideItem);
//                        LogUtils.d(newsSlideItem.getBody().getSlides().get(0).getImage());
////                        mNewsSlideItem = newsSlideItem;
//                    }
//                }
//        );
    }


    private void fetch(String url, int pager) {
        Log.d("lal", url + pager);
        OkHttpClientManager.getAsyn(
//                "http://api.iclient.ifeng.com/ClientNews?id=SYLB10,SYDT10&page=1",
                url + pager,
                new MyResultCallback<List<News>>() {

                    @Override
                    public void onBefore() {
                        super.onBefore();
//                        mLoadingView.postLoadState(UniversalLoadingView.State.LOADING);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
//                        mLoadingView.postLoadState(UniversalLoadingView.State.GONE);
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
//                        updateNewsListAdapter(mNewsList);
                    }
                }
        );
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getStatusBarColor());
            tintManager.setStatusBarTintEnabled(true);
        }
    }


    public int getStatusBarColor() {
        return getColorPrimary();
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }


}
