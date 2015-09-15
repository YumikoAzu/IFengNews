package com.yumikoazu.ifengnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.yumikoazu.ifengnews.bean.News;
import com.yumikoazu.ifengnews.ui.activity.NewsGalleryActivity;

import java.util.List;

/**
 * Created by joker on 2015/9/10.
 */
public class NewsRollVpAdapter extends DynamicPagerAdapter {
    private Context mContext;

    private List<News.ItemEntity> mItemEntities;

    public NewsRollVpAdapter(Context context, List<News.ItemEntity> itemEntities) {
        mContext = context;
        mItemEntities = itemEntities;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView view = new ImageView(container.getContext());
        Glide.with(mContext)
             .load(mItemEntities.get(position).getThumbnail()).centerCrop().crossFade()
             .into(view);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, NewsGalleryActivity.class);
                        String id = mItemEntities.get(position).getId();
                        intent.putExtra("galleryId",id);
                        mContext.startActivity(intent);
                    }
                }
        );
        return view;
    }

    @Override
    public int getCount() {
        return mItemEntities.size();
    }
}
