package com.blankcat.android.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.blankcat.android.R
import com.blankcat.android.base.BaseAdapter
import com.blankcat.android.base.BaseViewHolder
import com.blankcat.android.bean.HomeBean
import com.blankcat.android.utils.ImageUtils
import java.util.ArrayList

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
class MainAdapter : BaseAdapter {
        var mOnItemClickListener: (( view:View,pos: Int) -> Unit)? = null
        fun setOnItemClickListenerByLambda(listener: (( view:View,pos: Int) -> Unit)) {
            mOnItemClickListener = listener
        }

    lateinit var mTimeTv: TextView
    lateinit var mNameTv:TextView
    lateinit var mFlagTv:TextView
    lateinit var mStaTv:TextView
    lateinit var mStaBlueTv:TextView 
    lateinit var mNotDataTv:TextView
    lateinit var flagTwoTv:TextView
    lateinit var mPersonIv: ImageView
    lateinit var mDataFl: RelativeLayout
    var datas: ArrayList<HomeBean.ResultsBean.BookingListBean>
    constructor(datas: ArrayList<HomeBean.ResultsBean.BookingListBean>){
        this.datas = datas
    }

    override fun findView(holder: BaseViewHolder) {
        mTimeTv = holder.getView(R.id.time_tv)
        mNameTv = holder.getView(R.id.person_name_tv)
        mFlagTv = holder.getView(R.id.flag_tv)
        flagTwoTv = holder.getView(R.id.flag_two_tv)
        mStaTv = holder.getView(R.id.sta_tv)
        mPersonIv = holder.getView(R.id.person_iv)

        mStaBlueTv = holder.getView(R.id.sta_blue_tv)
        mNotDataTv = holder.getView(R.id.not_data_tv)
        mDataFl = holder.getView(R.id.data_fl)


    }
    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val bookingListBean = datas[position]
        if (bookingListBean.isShow as Boolean) {
            mTimeTv.visibility = View.VISIBLE
        } else {
            mTimeTv.visibility = View.INVISIBLE
        }
        if (bookingListBean.bookingTime!!.length === 1) {
            mTimeTv.text = "0" + bookingListBean.bookingTime + ":00"
        } else {
            mTimeTv.setText(bookingListBean.bookingTime + ":00")
        }
        if (0 == bookingListBean.bookingId) run {
            mDataFl.visibility = View.GONE
            mNotDataTv.visibility = View.VISIBLE
        } else {
            mDataFl.visibility = View.VISIBLE
            mNotDataTv.visibility = View.GONE
            mStaTv.visibility = View.VISIBLE
            mStaBlueTv.visibility = View.GONE
            mNameTv.setText(bookingListBean.userName)
            if( bookingListBean.userImg !=null)
                ImageUtils.showImage(mPersonIv.context, bookingListBean.userImg as String , mPersonIv)
            if (0 == bookingListBean.isFirst) {
                mFlagTv.visibility = View.VISIBLE
                flagTwoTv.visibility = View.GONE
            } else {
                mFlagTv.visibility = View.GONE
                flagTwoTv.visibility = View.VISIBLE
            }
            when (bookingListBean.bookingStatus) {
                0 -> {
                    mStaBlueTv.visibility = View.VISIBLE
                    mStaTv.visibility = View.GONE
                }
                1 -> {
                    mStaBlueTv.visibility = View.GONE
                    mStaTv.visibility = View.VISIBLE
                    mStaTv.text = "已过期"
                }
                2 -> {
                    mStaBlueTv.visibility = View.GONE
                    mStaTv.visibility = View.VISIBLE
                    mStaTv.text = "调理中"
                }
                3 -> {
                    mStaBlueTv.visibility = View.GONE
                    mStaTv.visibility = View.VISIBLE
                    mStaTv.text = "待支付"
                }
                4 -> {
                    mStaBlueTv.visibility = View.GONE
                    mStaTv.visibility = View.VISIBLE
                    mStaTv.text = "已完成"
                }
                5 -> {
                    mStaBlueTv.visibility = View.GONE
                    mStaTv.visibility = View.VISIBLE
                    mStaTv.text = "已取消"
                }
                6 -> {
                    mStaBlueTv.visibility = View.GONE
                    mStaTv.visibility = View.VISIBLE
                    mStaTv.text = "已接诊"
                }
            }
        }
    }


    override fun getLayoutID(position: Int): Int {
        return R.layout.item_home_two
    }

    override fun clickable(): Boolean {
        return true
    }

    override fun getItemCount(): Int {
        return datas.size
    }
    override fun onItemClick(view: View, position: Int) {
        super.onItemClick(view, position)
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener!!.invoke(view,position)
        }
    }
}