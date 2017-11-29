package com.werb.gankwithzhihu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.werb.gankwithzhihu.R;
import com.werb.gankwithzhihu.bean.news.NewsBean;
import com.werb.gankwithzhihu.ui.activity.NewsWebActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * user：lqm
 * desc：新闻适配器
 */

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<NewsBean> mList;

    public NewsListAdapter(Context mContext, List<NewsBean> mList) {
        this.context = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsHolder){
            NewsHolder newsHolder = (NewsHolder) holder;
            newsHolder.bindItem(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class NewsHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_news)
        ImageView ivNews;
        @Bind(R.id.tv_news_title)
        TextView tvNewsTitle;
        @Bind(R.id.card_news)
        CardView cardView;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(NewsBean newsBean){
            tvNewsTitle.setText(newsBean.getTitle());
            Glide.with(context).load(newsBean.getThumbnail_pic_s()).centerCrop().into(ivNews);

            cardView.setOnClickListener(v -> {
                context.startActivity(NewsWebActivity.newIntent(context,newsBean.getUrl()));
            });

        }

    }

}
