package com.yumikoazu.ifengnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yumikoazu.ifengnews.ui.fragment.NewsFragment;

import java.util.List;

/**
 * Created by joker on 2015/9/7.
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {
    private List<String> mCategoryList = null;


    public NewsPagerAdapter(FragmentManager fm, List<String> categoryList) {
        super(fm);
        mCategoryList = categoryList;
    }

    @Override
    public Fragment getItem(int position) {

        return new NewsFragment();
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null != mCategoryList ? mCategoryList.get(position) : null;
    }



}
