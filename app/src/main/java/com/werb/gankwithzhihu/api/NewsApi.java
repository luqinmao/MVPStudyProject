package com.werb.gankwithzhihu.api;

import com.werb.gankwithzhihu.bean.news.NewsData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * autour: lqm
 * desc: 新闻
 */
public interface NewsApi {

    //http://v.juhe.cn/toutiao/index
    //type=top&key=d050ce77241cf978f252dbd4db9ba00f

    @GET("index")
    Observable<NewsData> getNewsData(@Query("type") String type, @Query("key") String key);


//    @FormUrlEncoded
//    @POST("index")
//    Observable<NewsData> getNewsData(
//            @Field("type") String type,
//            @Field("key") String key);

}
