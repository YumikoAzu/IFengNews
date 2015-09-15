package com.yumikoazu.ifengnews.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.bean.NewsSlideItem;
import com.yumikoazu.ifengnews.common.ShowOrHideListener;
import com.yumikoazu.ifengnews.ui.view.CircularProgress;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by joker on 2015/9/10.
 */
public class NewsGalleryAdapter extends PagerAdapter implements PhotoViewAttacher.OnViewTapListener {

    private PhotoView mPagerItemGalleryImageView;
    private CircularProgress mLoadingView;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<NewsSlideItem.BodyEntity.SlidesEntity> mSlidesEntity;

    private NewsAsyncTask mNewsAsyncTask;

    private Handler mHandler;

    private ShowOrHideListener mShowOrHideListener;

    private boolean isShow = true;

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public void setShowOrHideListener(ShowOrHideListener showOrHideListener) {
        mShowOrHideListener = showOrHideListener;
    }

    public NewsGalleryAdapter(Context context, List<NewsSlideItem.BodyEntity.SlidesEntity> slidesEntity, Handler handler) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mSlidesEntity = slidesEntity;
        mHandler = handler;

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.pager_item_gallery_image, null);
        mPagerItemGalleryImageView = (PhotoView) view.findViewById(R.id.pager_item_gallery_image_view);
        mLoadingView = (CircularProgress) view.findViewById(R.id.loadingView);
//        mNewsAsyncTask = new NewsAsyncTask(mLoadingView);

        mPagerItemGalleryImageView.setOnViewTapListener(this);

//        loadImage(0);
        Log.d("8888", mSlidesEntity.get(0).getImage());
        Glide.with(mContext).load(mSlidesEntity.get(0).getImage()).fitCenter().crossFade().into(mPagerItemGalleryImageView);
        container.addView(view);
        return view;
    }

    public void loadImage(final int position) {
        mLoadingView.setVisibility(View.VISIBLE);
        mHandler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(mContext).load(mSlidesEntity.get(position).getImage()).centerCrop().crossFade().into(mPagerItemGalleryImageView);
                        mLoadingView.setVisibility(View.GONE);
                    }
                }, 2000
        );
    }

    public void updateImage(int position) {
//        mNewsAsyncTask.execute(mSlidesEntity.get(position).getImage());
//        loadImage(position);
        Glide.with(mContext).load(mSlidesEntity.get(position).getImage()).fitCenter().crossFade().into(mPagerItemGalleryImageView);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mSlidesEntity.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onViewTap(View view, float x, float y) {

        Log.d("show", isShow + "");
        if (isShow) {
            isShow = false;
            if (mShowOrHideListener != null) {
                mShowOrHideListener.hide();
            }

        } else {
            isShow = true;
            if (mShowOrHideListener != null) {
                mShowOrHideListener.show();
            }

        }
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, Void> {

        private String url;

        private CircularProgress mCircularProgress;

        public NewsAsyncTask(CircularProgress circularProgress) {
            mCircularProgress = circularProgress;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCircularProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... params) {
            url = params[0];
            Glide.with(mContext).load(url).centerCrop().crossFade().into(mPagerItemGalleryImageView);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mCircularProgress.setVisibility(View.GONE);
        }
    }
}
