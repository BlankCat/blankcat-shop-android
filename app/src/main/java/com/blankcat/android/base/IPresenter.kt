package com.blankcat.android.base
/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
 interface IPresenter<T:IView> {

    fun  detachView()

    fun attachView( view:T)
}
