package com.blankcat.android.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return BaseViewHolder(LayoutInflater.from(parent!!.getContext()).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (clickable()) {
            holder!!.getConvertView().setClickable(true)
            holder.getConvertView().setOnClickListener(View.OnClickListener { v ->
                onItemClick(v, position)
            })
        }
        onBindView(holder!!, holder.getLayoutPosition())
    }

    abstract fun onBindView(holder: BaseViewHolder, position: Int)


    override fun getItemViewType(position: Int): Int {
        return getLayoutID(position)
    }

    abstract fun getLayoutID(position: Int): Int

    abstract fun clickable(): Boolean

    open fun onItemClick(v: View, position: Int) {
    }
}