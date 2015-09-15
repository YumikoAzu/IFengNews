package com.yumikoazu.ifengnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.bean.News;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joker on 2015/9/10.
 */
public class NewsListAdapter extends BaseAdapter {

    public static final int DOC_TYPE = 0;
    public static final int SLIDE_TYPE = 1;
    private Context mContext;
    private List<News.ItemEntity> mNewsList;
    private LayoutInflater mLayoutInflater;

    private int currentType;

    public NewsListAdapter(Context context, List<News.ItemEntity> newsList) {
        mContext = context;
        mNewsList = newsList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void onDateChange(List<News.ItemEntity> newsList) {
        mNewsList.addAll(newsList);
        this.notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        News.ItemEntity itemEntity = mNewsList.get(position);
        String type = itemEntity.getType();

        if (type.equals("doc")) {
            return DOC_TYPE;
        }else if (type.equals("slide")){
            return SLIDE_TYPE;
        }
        return 3;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News.ItemEntity itemEntity = mNewsList.get(position);
        currentType = getItemViewType(position);

        DocHolder docHolder = null;
        SlideHolder slideHolder = null;

        if (convertView == null) {
            switch (currentType) {
                case DOC_TYPE:
                    convertView = mLayoutInflater.inflate(R.layout.list_item_news_list, parent, false);
                    docHolder = new DocHolder(convertView);
                    docHolder.mListItemNewsListTitle.setText(itemEntity.getTitle());
                    Glide.with(mContext)
                         .load(itemEntity.getThumbnail()).centerCrop().crossFade()
                         .into(docHolder.mListItemNewsListImage);
                    docHolder.mListItemNewsListSource.setText(itemEntity.getSource());
                    docHolder.mListItemNewsListDate.setText(itemEntity.getUpdateTime());
                    convertView.setTag(docHolder);
                    break;

                case SLIDE_TYPE:
                    convertView = mLayoutInflater.inflate(R.layout.list_item_news_list_slide, parent, false);
                    slideHolder = new SlideHolder(convertView);
                    slideHolder.mListItemNewsListSlideTitle.setText(itemEntity.getTitle());
                    News.ItemEntity.StyleEntity styleEntitie = itemEntity.getStyle();
                    List<String> imags = styleEntitie.getImages();
                    Glide.with(mContext)
                         .load(imags.get(0)).centerCrop().crossFade()
                         .into(slideHolder.mSlideIVOne);
                    Glide.with(mContext)
                         .load(imags.get(1)).centerCrop().crossFade()
                         .into(slideHolder.mSlideIVTwo);

                    Glide.with(mContext)
                         .load(imags.get(2)).centerCrop().crossFade()
                         .into(slideHolder.mSlideIVThree);

                    convertView.setTag(slideHolder);
                    break;
            }
        } else {
            switch (currentType) {
                case DOC_TYPE:
                    docHolder = (DocHolder) convertView.getTag();
                    docHolder.mListItemNewsListTitle.setText(itemEntity.getTitle());
                    Glide.with(mContext)
                         .load(itemEntity.getThumbnail()).centerCrop().crossFade()
                         .into(docHolder.mListItemNewsListImage);
                    docHolder.mListItemNewsListSource.setText(itemEntity.getSource());
                    docHolder.mListItemNewsListDate.setText(itemEntity.getUpdateTime());
                    break;

                case SLIDE_TYPE:
                    slideHolder = (SlideHolder) convertView.getTag();
                    slideHolder.mListItemNewsListSlideTitle.setText(itemEntity.getTitle());
                    News.ItemEntity.StyleEntity styleEntitie = itemEntity.getStyle();
                    List<String> imags = styleEntitie.getImages();
                    Glide.with(mContext)
                         .load(imags.get(0)).centerCrop().crossFade()
                         .into(slideHolder.mSlideIVOne);
                    Glide.with(mContext)
                         .load(imags.get(1)).centerCrop().crossFade()
                         .into(slideHolder.mSlideIVTwo);

                    Glide.with(mContext)
                         .load(imags.get(2)).centerCrop().crossFade()
                         .into(slideHolder.mSlideIVThree);
                    break;
            }
        }


        return convertView;
    }


    static class DocHolder {

        @Bind(R.id.list_item_news_list_title)
        TextView mListItemNewsListTitle;
        @Bind(R.id.list_item_news_list_image)
        ImageView mListItemNewsListImage;
        @Bind(R.id.list_item_news_list_source)
        TextView mListItemNewsListSource;
        @Bind(R.id.list_item_news_list_date)
        TextView mListItemNewsListDate;

        public DocHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class SlideHolder {
        @Bind(R.id.list_item_news_list_slide_title)
        TextView mListItemNewsListSlideTitle;
        @Bind(R.id.slideIVOne)
        ImageView mSlideIVOne;
        @Bind(R.id.slideIVTwo)
        ImageView mSlideIVTwo;
        @Bind(R.id.slideIVThree)
        ImageView mSlideIVThree;

        public SlideHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
