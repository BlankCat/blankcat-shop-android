package com.blankcat.android.common.network

import com.blankcat.android.bean.HomeBean
import com.blankcat.android.bean.UserMessageBean
import com.blankcat.android.constant.UrlConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable
import java.util.Map

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: 接口服务
 */
public interface RetrofitApiService {
    @GET(UrlConfig.HOME)
    fun toHome(@Path("version")  version:String, @Query("bookingDate")  bookingDate:String, @Query("userId")  userId:String): Observable<HomeBean>

    @GET(UrlConfig.SELECTUSERINFO)
    fun toUserInfoMessage(@Path("version")  version:String,   @Query("token") map: Map<String, String>):Observable<UserMessageBean>

}