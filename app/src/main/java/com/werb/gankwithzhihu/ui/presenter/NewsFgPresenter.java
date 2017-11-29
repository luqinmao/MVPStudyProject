package com.werb.gankwithzhihu.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.bean.news.NewsBean;
import com.werb.gankwithzhihu.bean.news.NewsData;
import com.werb.gankwithzhihu.ui.adapter.NewsListAdapter;
import com.werb.gankwithzhihu.ui.base.BasePresenter;
import com.werb.gankwithzhihu.ui.view.NewsFgView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * user：lqm
 * desc：
 */

public class NewsFgPresenter extends BasePresenter<NewsFgView> {


    private Context context;
    private NewsFgView newsFgView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private boolean isLoadMore = false;
    private int page = 1;
    private int lastVisibleItem;
    private NewsListAdapter adapter;
    private List<NewsBean> mData;

    public NewsFgPresenter(Context context) {
        this.context = context;
    }

    public void getNewsData(){
        newsFgView = getView();
        if (newsFgView != null) {
            mRecyclerView = newsFgView.getRecyclerView();
            layoutManager = newsFgView.getLayoutManager();
            if (isLoadMore) {
                page = page + 1;
            }

            newsApi.getNewsData("top", "d050ce77241cf978f252dbd4db9ba00f")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsData -> {
                        displayNewsList(newsData);
                    },this::loadError);

//            newsApi.getNewsData("top","d050ce77241cf978f252dbd4db9ba00f")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Subscriber<NewsData>() {
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            loadError(e);
//                        }
//
//                        @Override
//                        public void onNext(NewsData newsData) {
//                            displayNewsList(newsData);
//                        }
//                    });

        }


    }

    private void displayNewsList(NewsData newsData) {
        if (isLoadMore){
            mData.addAll(newsData.getResult().getData());
            adapter.notifyDataSetChanged();
        }else{
            mData = newsData.getResult().getData();
            adapter = new NewsListAdapter(context,mData);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        newsFgView.setDataRefresh(false);

    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    public  void scrollRecyclerView(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItem + 1 == layoutManager.getItemCount()) {
                        isLoadMore = true;
                        new Handler().postDelayed(() -> getNewsData(), 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }


}
