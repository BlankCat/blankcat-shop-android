package com.blankcat.android.mvp.presenter
import android.content.Context
import com.blankcat.android.bean.HomeBean
import com.blankcat.android.mvp.MVPContract
import com.blankcat.android.mvp.MVPContract.IHomePresenter
import com.blankcat.android.mvp.MVPModle
import rx.Subscriber
import rx.subscriptions.CompositeSubscription

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
class HomePresenter : IHomePresenter{

    lateinit var iHomeView :MVPContract.IHomeView
    lateinit var iHomeModel:MVPContract.IHomeModel
    lateinit var compositeSubscription: CompositeSubscription

    override fun attachView(view: MVPContract.IHomeView) {
        this.iHomeView = view
        this.iHomeModel = MVPModle()
        this.compositeSubscription = CompositeSubscription()
    }

    override fun detachView() {
        this.compositeSubscription.clear()
    }

    override fun loadHome(context: Context, time: String, userId: String) {
        iHomeView.showloadingDialog()
        iHomeModel.toHome(object : Subscriber<HomeBean>() {
            override fun onCompleted() {
            }
            override fun onError(e: Throwable?) {
                iHomeView.onFailed("网络异常")
                iHomeView.hideLoadingDialog()
            }

            override fun onNext(homeBean: HomeBean?) {
                if ("0" == homeBean!!.code as String) {
                    iHomeView.onSuccess(homeBean)
                } else {
                    iHomeView.onFailed(homeBean.msg as String)
                }
                iHomeView.hideLoadingDialog()
            }
        },time,userId)
    }
}
