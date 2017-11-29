package com.werb.gankwithzhihu.api;

import com.werb.gankwithzhihu.bean.news.NewsData;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * autour: lqm
 * desc: 新闻
 */
public interface NewsApi {

    //http://v.juhe.cn/toutiao/index
    //type=top&key=d050ce77241cf978f252dbd4db9ba00f
//    @GET("index")
//    Observable<NewsData> getNewsData(@Query("type") String type, @Query("key") String key);

    @POST("index")
    Observable<NewsData> getNewsData(@Query("type") String type, @Query("key") String key);

    @POST("index")
    Observable<NewsData> reportComment(
            @Query("access_token") String access_token,
            @Body NewsData bean);



}
