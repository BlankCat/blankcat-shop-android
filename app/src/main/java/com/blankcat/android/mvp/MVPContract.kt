package com.blankcat.android.mvp

import android.content.Context
import com.blankcat.android.base.IModle
import com.blankcat.android.base.IPresenter
import com.blankcat.android.base.IView
import com.blankcat.android.bean.HomeBean
import rx.Subscriber

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: MVP基类定义
 */
class MVPContract {
    /**
     * 首页
     */
    interface IHomeView : IView {
        fun onSuccess( homeBean:HomeBean)
        fun onFailed( msg:String)
    }
    interface IHomeModel : IModle {
        fun toHome(subscriber: Subscriber<HomeBean>, time:String, userId:String)
    }
    interface IHomePresenter : IPresenter<IHomeView> {
        fun loadHome(context: Context, time:String, userId:String)
    }




}