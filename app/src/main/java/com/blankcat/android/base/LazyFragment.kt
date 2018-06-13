package com.blankcat.android.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import butterknife.ButterKnife
import com.afollestad.materialdialogs.MaterialDialog
import com.blankcat.android.R


/**
 * <h1>懒加载Fragment</h1> 只有创建并显示的时候才会调用onCreateViewLazy方法<br>
 * <br>
 * <p/>
 * 懒加载的原理onCreateView的时候Fragment有可能没有显示出来。<br>
 * 但是调用到setUserVisibleHint(boolean isVisibleToUser),isVisibleToUser =
 * true的时候就说明有显示出来<br>
 * 但是要考虑onCreateView和setUserVisibleHint的先后问题所以才有了下面的代码
 * <p/>
 * 注意：<br>
 * 《1》原先的Fragment的回调方法名字后面要加个Lazy，比如Fragment的onCreateView方法， 就写成onCreateViewLazy <br>
 * 《2》使用该LazyFragment会导致多一层布局深度
 *
 * @author LuckyJayce
 */
public class LazyFragment : BaseFragment() {

    lateinit var  loadingDialog:MaterialDialog

    var isInit:Boolean = false//真正要显示的View是否已经被初始化（正常加载）
    var  savedInstanceState:Bundle?=null
    val  INTENT_BOOLEAN_LAZYLOAD:String = "intent_boolean_lazyLoad"
    var  isLazyLoad:Boolean = true
    var layout:FrameLayout?=null
    var isStart:Boolean = false//是否处于可见状态，in the screen

    /**
     * 重写父类方法 父类方法必须定义成 open fun
     */
    override fun onCreateView(savedInstanceState: Bundle?) {
        Log.d("TAG", "onCreateView() : " + "getUserVisibleHint():" + getUserVisibleHint())
        super.onCreateView(savedInstanceState)
        var bundle = getArguments()
        if (bundle != null) {
            isLazyLoad = bundle.getBoolean(INTENT_BOOLEAN_LAZYLOAD, isLazyLoad)
        }
        //判断是否懒加载
        if (isLazyLoad) {
            //一旦isVisibleToUser==true即可对真正需要的显示内容进行加载
            if (getUserVisibleHint() && !isInit) {
                this.savedInstanceState = savedInstanceState
                onCreateViewLazy(savedInstanceState)
                isInit = true
            } else {
                //进行懒加载
                layout =  FrameLayout(getApplicationContext())
                layout!!.setLayoutParams( LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
                var view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_lazy_loading, null)
                layout!!.addView(view)
                super.setContentViewBykt(layout)
            }
        } else {
            //不需要懒加载，开门江山，调用onCreateViewLazy正常加载显示内容即可
            onCreateViewLazy(savedInstanceState)
            isInit = true
        }
    }




    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d("TAG", "setUserVisibleHint() called with: " + "isVisibleToUser = [" + isVisibleToUser + "]")
        //一旦isVisibleToUser==true即可进行对真正需要的显示内容的加载

        //可见，但还没被初始化
        if (isVisibleToUser && !isInit && getContentViewByKt() != null) {
            onCreateViewLazy(savedInstanceState)
            isInit = true
            onResumeLazy()
        }
        //已经被初始化（正常加载）过了
        if (isInit && getContentViewByKt() != null) {
            if (isVisibleToUser) {
                isStart = true
                onFragmentStartLazy()
            } else {
                isStart = false
                onFragmentStopLazy()
            }
        }
    }

    override fun setContentViewBykt(view: View?) {
        //判断若isLazyLoad==true,移除所有lazy view，加载真正要显示的view
        if (isLazyLoad && getContentViewByKt() != null && getContentViewByKt()!!.getParent() != null) {
            layout!!.removeAllViews()
            layout!!.addView(view)
            ButterKnife.bind(this, view!!)
        }
        //否则，开门见山，直接加载
        else {
            ButterKnife.bind(this, view!!)
            super.setContentViewBykt(view!!)
        }
    }



    override fun onStart() {
        Log.d("TAG", "onStart() : " + "getUserVisibleHint():" + getUserVisibleHint())
        super.onStart()
        if (isInit && !isStart && getUserVisibleHint()) {
            isStart = true
            onFragmentStartLazy()
        }
    }
    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop() called: " + "getUserVisibleHint():" + getUserVisibleHint())
        if (isInit && isStart && getUserVisibleHint()) {
            isStart = false
            onFragmentStopLazy()
        }
    }
    //当Fragment被滑到可见的位置时，调用
    fun onFragmentStartLazy() {
        Log.d("TAG", "onFragmentStartLazy() called with: " + "")
    }

    //当Fragment被滑到不可见的位置，offScreen时，调用
    fun onFragmentStopLazy() {
        Log.d("TAG", "onFragmentStopLazy() called with: " + "")
    }

    fun onCreateViewLazy( savedInstanceState:Bundle?) {
        Log.d("TAG", "onCreateViewLazy() called with: " + "savedInstanceState = [" + savedInstanceState + "]")
    }

    fun onResumeLazy() {
        Log.d("TAG", "onResumeLazy() called with: " + "")
    }

    fun onPauseLazy() {
        Log.d("TAG", "onPauseLazy() called with: " + "")
    }

    fun onDestroyViewLazy() {

    }

    override fun onResume() {
        Log.d("TAG", "onResume() : " + "getUserVisibleHint():" + getUserVisibleHint())
        super.onResume()
        if (isInit) {
            onResumeLazy()
        }
    }
    override fun onPause() {
        Log.d("TAG", "onPause() : " + "getUserVisibleHint():" + getUserVisibleHint())
        super.onPause()
        if (isInit) {
            onPauseLazy()
        }
    }

    override fun onDestroyView() {
        Log.d("TAG", "onDestroyView() : " + "getUserVisibleHint():" + getUserVisibleHint())
        super.onDestroyView()
        if (isInit) {
            onDestroyViewLazy()
        }
        isInit = false
    }

}