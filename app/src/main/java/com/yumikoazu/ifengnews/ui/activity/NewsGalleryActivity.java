package com.yumikoazu.ifengnews.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.squareup.okhttp.Request;
import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.adapter.NewsGalleryAdapter;
import com.yumikoazu.ifengnews.bean.NewsSlideItem;
import com.yumikoazu.ifengnews.common.ShowOrHideListener;
import com.yumikoazu.ifengnews.net.MyResultCallback;
import com.yumikoazu.ifengnews.net.OkHttpClientManager;
import com.yumikoazu.ifengnews.widgets.HackyViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joker on 2015/9/11.
 */
public class NewsGalleryActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, ShowOrHideListener {

    @Bind(R.id.news_image_gallery_pager)
    HackyViewPager mNewsImageGalleryPager;
    @Bind(R.id.news_image_gallery_description)
    TextView mNewsImageGalleryDescription;
    @Bind(R.id.news_image_gallery_indicator)
    TextView mNewsImageGalleryIndicator;
    @Bind(R.id.news_image_gallery_bottom_container)
    LinearLayout mNewsImageGalleryBottomContainer;
    @Bind(R.id.common_toolbar)
    Toolbar mCommonToolbar;

    private NewsSlideItem mNewsSlideItem;
    private String url;
    private NewsGalleryAdapter mNewsGalleryAdapter;


    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_image_gallery);
        ButterKnife.bind(this);
        setSupportActionBar(mCommonToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {

            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        url = getIntent().getStringExtra("galleryId");
        Log.d("url", url);

        fetch(url);


        mNewsImageGalleryPager.setOnPageChangeListener(this);

    }

    private void initData() {
        mCommonToolbar.setTitle(mNewsSlideItem.getBody().getTitle());
        mNewsImageGalleryDescription.setText(mNewsSlideItem.getBody().getSlides().get(0).getDescription());
        mNewsImageGalleryIndicator.setText(1 + "/" + mNewsSlideItem.getBody().getSlides().size());
    }

    private void fetch(String url) {
        Log.d("tes", url);

        OkHttpClientManager.getAsyn(
                url,
                new MyResultCallback<NewsSlideItem>() {


                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(NewsSlideItem newsSlideItem) {
                        Log.d("tes111", "66666");
                        LogUtils.d(newsSlideItem);
                        mNewsSlideItem = newsSlideItem;
                        setUpGalleryAdapter(newsSlideItem);
                        initData();
                    }
                }
        );
    }

    private void setUpGalleryAdapter(NewsSlideItem newsSlideItem) {
        mNewsGalleryAdapter = new NewsGalleryAdapter(this, newsSlideItem.getBody().getSlides(), mHandler);
        mNewsGalleryAdapter.setShowOrHideListener(this);
        mNewsImageGalleryPager.setAdapter(mNewsGalleryAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String text = position + 1 + "/" + mNewsSlideItem.getBody().getSlides().size();
        mNewsImageGalleryIndicator.setText(text);
        mNewsImageGalleryDescription.setText(mNewsSlideItem.getBody().getSlides().get(position).getDescription());
        mNewsGalleryAdapter.updateImage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void show() {
        Animation topIn = AnimationUtils.loadAnimation(this, R.anim.top_in);
        Animation bottomIn = AnimationUtils.loadAnimation(this, R.anim.bottom_in);
        topIn.setDuration(1000);
        bottomIn.setDuration(1000);
        mCommonToolbar.startAnimation(topIn);
        mNewsImageGalleryBottomContainer.startAnimation(bottomIn);
        Log.d("show", 1 + "");
//        mNewsGalleryAdapter.setIsShow(true);
    }

    @Override
    public void hide() {
        Animation topOut = AnimationUtils.loadAnimation(this, R.anim.top_out);
        Animation bottomOut = AnimationUtils.loadAnimation(this, R.anim.bottom_out);
        topOut.setDuration(1000);
        bottomOut.setDuration(1000);
        mCommonToolbar.startAnimation(topOut);
        mNewsImageGalleryBottomContainer.startAnimation(bottomOut);

        Log.d("show", 2 + "");
//        mNewsGalleryAdapter.setIsShow(false);
    }
}
