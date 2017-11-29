package com.werb.gankwithzhihu.ui.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.view.NewsWebView;

/**
 * user：lqm
 * desc：
 */

public class NewsWebPresenter extends BasePresenter<NewsWebView> {

    private Activity activity;

    public NewsWebPresenter(Activity activity) {
        this.activity = activity;
    }


    public void setWebView(String url) {
        NewsWebView newsWebView = getView();
        ProgressBar progressBar = newsWebView.getProgressBar();
        WebView webView = newsWebView.getWebView();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true); //

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);

            }

            /**
             * 所有跳转的链接都在此方法中回调
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                activity.setTitle(title);
                super.onReceivedTitle(view, title);
            }
        });

        webView.loadUrl(url);
    }
}
