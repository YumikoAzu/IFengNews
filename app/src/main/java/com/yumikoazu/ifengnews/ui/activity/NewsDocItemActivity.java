package com.yumikoazu.ifengnews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yumikoazu.ifengnews.R;
import com.yumikoazu.ifengnews.bean.NewsDocItem;
import com.yumikoazu.ifengnews.widgets.BrowserLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joker on 2015/9/12.
 */
public class NewsDocItemActivity extends AppCompatActivity {


    @Bind(R.id.common_toolbar)
    Toolbar mCommonToolbar;
    @Bind(R.id.common_web_browser_layout)
    BrowserLayout mCommonWebBrowserLayout;

    private String url;

    private String title;

    private NewsDocItem mNewsDocItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        ButterKnife.bind(this);

        setSupportActionBar(mCommonToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {

            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");

        mCommonToolbar.setTitle(title);
        mCommonWebBrowserLayout.loadUrl(url);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }
}
