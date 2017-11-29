package com.werb.gankwithzhihu.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * user：lqm
 * desc：新闻
 */

public interface NewsFgView {

    void setDataRefresh(Boolean isRefresh);
    LinearLayoutManager getLayoutManager();
    RecyclerView getRecyclerView();

}
