package com.werb.gankwithzhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.ui.base.MVPBaseFragment;
import com.werb.gankwithzhihu.ui.presenter.NewsFgPresenter;
import com.werb.gankwithzhihu.ui.view.NewsFgView;

import butterknife.Bind;

/**
 * user：lqm
 * desc：
 */

public class NewsFragment extends MVPBaseFragment<NewsFgView, NewsFgPresenter> implements NewsFgView {

    @Bind(R.id.content_list)
    RecyclerView contentList;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected NewsFgPresenter createPresenter() {
        return new NewsFgPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View rootView) {
         linearLayoutManager =new LinearLayoutManager(getContext());
        contentList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataRefresh(true);
        mPresenter.getNewsData();
        mPresenter.scrollRecyclerView();

    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getNewsData();
    }

    @Override
    public void setDataRefresh(Boolean isRefresh) {
        setRefresh(isRefresh);
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return linearLayoutManager;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return contentList;
    }

}
