package com.blankcat.android.common.network;

import android.support.annotation.NonNull;

import com.blankcat.android.base.BaseApp;
import com.blankcat.android.bean.HomeBean;
import com.blankcat.android.bean.UserMessageBean;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static com.blankcat.android.constant.UrlConfig.BASE_URL;


public class HttpHelper {

    private static volatile HttpHelper singleton;
    private RetrofitApiService service;

    private HttpHelper() {
        service = getRetrofitApiService();
    }

    public static HttpHelper getInstance() {
        if (singleton == null) {
            synchronized (HttpHelper.class) {
                if (singleton == null) {
                    singleton = new HttpHelper();
                }
            }
        }
        return singleton;
    }

    /**
     * 添加安全证书时需要使用
     *
     * @return 添加了安全证书的client
     */
    private OkHttpClient createClient() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                //忽略域名验证
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        //添加域名验证,这里是默认所有的都信任
                        return true;
                    }
                })
                //添加安全证书
                .sslSocketFactory(SSLHelper.INSTANCE.getSSLCertificate(BaseApp.getInstance()), new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                })
                .build();
        return client;
    }


    private RetrofitApiService getRetrofitApiService() {
        return getRetrofit().create(RetrofitApiService.class);
    }

    @NonNull
    private Retrofit getRetrofit() {
        HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        return retrofit;
    }

    /**首页*/
    public Observable<HomeBean> toHome(String time, String userId) {
        return service.toHome("v" + BaseApp.VERSIONCODE, time, userId);
    }

    /**个人中心*/
    public Observable<UserMessageBean> toUserInfoMessage(HashMap<String, String> map) {
        return service.toUserInfoMessage("v" + BaseApp.VERSIONCODE, map);
    }

}
