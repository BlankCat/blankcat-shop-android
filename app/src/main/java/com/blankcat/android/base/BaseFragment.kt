package com.blankcat.android.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
  open class BaseFragment : Fragment() {
      var  inflater:LayoutInflater?=null
      var  contentView :View?=null
      var  contexts:Context?=null
      var  container:ViewGroup?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contexts = getActivity().getApplicationContext()
    }


    //子类通过重写onCreateView，调用setOnContentView进行布局设置，否则contentView==null，返回null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater as LayoutInflater
        this.container = container as ViewGroup
        onCreateView(savedInstanceState)
        if (contentView == null)
            return super.onCreateView(inflater, container, savedInstanceState)
        return contentView
    }
    open fun  onCreateView( savedInstanceState:Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        contentView = null
        container = null
        inflater = null
    }


    fun  getApplicationContext() :Context??{
        return contexts
    }

    fun  setContentView( layoutResID:Int) {
        setContentViewBykt(inflater!!.inflate(layoutResID, container, false) )
    }

    open fun setContentViewBykt(view:View?) {
        this.contentView = view
    }

    open fun  getContentViewByKt():View?{
        return contentView
    }

    /**
     * 接口返回null 返回值后面加？
     */
    fun  findViewById( id:Int) :View??{
        if (contentView != null)
            return contentView!!.findViewById(id)
        return null
    }

    // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed

    /**
     * 反射机制的语法糖
     */
    override fun onDetach() {
        Log.d("TAG", "onDetach() : ")
        super.onDetach()
        try {
            var childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.setAccessible(true)
            childFragmentManager.set(this, null)

        } catch ( e:NoSuchFieldException) {
            throw  RuntimeException(e)
        } catch ( e:IllegalAccessException) {
            throw  RuntimeException(e)
        }

    }
    override fun onDestroy() {
        Log.d("TAG", "onDestroy() : ")
        super.onDestroy()
    }

}