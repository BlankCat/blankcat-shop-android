package com.blankcat.android.mvp.presenter

import android.content.Context
import com.blankcat.android.bean.UserMessageBean
import com.blankcat.android.mvp.MVPContract
import com.blankcat.android.mvp.MVPModle
import rx.Subscriber
import rx.subscriptions.CompositeSubscription
import java.util.HashMap

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: 用户信息
 */
 class UserMessageInfoPresenter : MVPContract.IUserInfoMessagePresenter {

    lateinit var iUserInfoMessageView:MVPContract.IUserInfoMessageView
    lateinit var iUserInfoMessageModel:MVPContract.IUserInfoMessageModel
    lateinit var compositeSubscription:CompositeSubscription

    override fun attachView(view: MVPContract.IUserInfoMessageView) {
        this.iUserInfoMessageView = view
        this.iUserInfoMessageModel = MVPModle()
        this.compositeSubscription = CompositeSubscription()
    }

    override fun detachView() {
        this.compositeSubscription.clear()
    }

    override fun loadUserInfoMessage(context: Context, map: HashMap<String, String>) {
        this.iUserInfoMessageView.showloadingDialog()
        this.iUserInfoMessageModel.toUserInfoMessage(object :Subscriber<UserMessageBean>(){
            override fun onCompleted() {

            }
            override fun onError(e: Throwable?) {
                iUserInfoMessageView.onFailed("网络异常")
                iUserInfoMessageView.hideLoadingDialog()
            }
            override fun onNext(userMessageBean: UserMessageBean?) {
                if ("0" == userMessageBean!!.code) {
                    iUserInfoMessageView.onUserInfoMessageSuccess(userMessageBean)
                } else {
                    iUserInfoMessageView.onFailed(userMessageBean.msg as String)
                }
                iUserInfoMessageView.hideLoadingDialog()
            }
        },map)
    }

}