package com.werb.gankwithzhihu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.ui.base.MVPBaseActivity;
import com.werb.gankwithzhihu.ui.presenter.NewsWebPresenter;
import com.werb.gankwithzhihu.ui.view.NewsWebView;

import butterknife.Bind;

import static com.werb.gankwithzhihu.R.id.url_web;

/**
 * user：lqm
 * desc：新闻详情界面
 */

public class NewsWebActivity extends MVPBaseActivity<NewsWebView, NewsWebPresenter>
        implements NewsWebView {

    public static final String NEWS_URL = "news_url";

    @Bind(R.id.pb_progress)
    ProgressBar pbProgress;
    @Bind(url_web)
    WebView urlWeb;
    private String mUrl;

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, NewsWebActivity.class);
        intent.putExtra(NewsWebActivity.NEWS_URL, url);
        return intent;
    }

    @Override
    protected NewsWebPresenter createPresenter() {
        return new NewsWebPresenter(NewsWebActivity.this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_news_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrl = getIntent().getStringExtra(NEWS_URL);
        mPresenter.setWebView(mUrl);
    }

    @Override
    public ProgressBar getProgressBar() {
        return pbProgress;
    }

    @Override
    public WebView getWebView() {
        return urlWeb;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        urlWeb.destroy();
    }

}
