package com.yumikoazu.ifengnews.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.ui.view.CircularProgress;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by joker on 2015/9/11.
 */
public class Test extends Activity {

    @Bind(R.id.pager_item_gallery_image_view)
    PhotoView mPagerItemGalleryImageView;
    @Bind(R.id.loadingView)
    CircularProgress mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_item_gallery_image);
        ButterKnife.bind(this);

        mLoadingView.setVisibility(View.VISIBLE);
        mPagerItemGalleryImageView.setVisibility(View.GONE);
    }
}
