package com.blankcat.android.mvp

import android.content.Context
import com.blankcat.android.base.IModle
import com.blankcat.android.base.IPresenter
import com.blankcat.android.base.IView
import com.blankcat.android.bean.HomeBean
import com.blankcat.android.bean.UserMessageBean
import rx.Subscriber
import java.util.HashMap

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

    /**
     * 个人中心
     */
    interface IUserInfoMessageView : IView {
        fun onUserInfoMessageSuccess( userMessageBean: UserMessageBean)
        fun onFailed( msg:String)
    }
    interface IUserInfoMessageModel : IModle {
        fun toUserInfoMessage( subscriber:Subscriber<UserMessageBean>,  map: HashMap<String, String>)

    }
    interface IUserInfoMessagePresenter : IPresenter<IUserInfoMessageView> {
        fun loadUserInfoMessage( context:Context,  map: HashMap<String, String>)
    }



}