package com.blankcat.android.mvp;

import com.blankcat.android.bean.HomeBean
import com.blankcat.android.common.network.HttpHelper
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: 网络请求订阅数据
 */
public class MVPModle :MVPContract.IHomeModel {
    override fun toHome(subscriber: Subscriber<HomeBean>, time: String, userId: String) {
        HttpHelper.getInstance().toHome(time, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }
}
