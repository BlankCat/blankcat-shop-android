package com.blankcat.android.utils

import com.blankcat.android.bean.HomeBean
import java.util.*
import java.util.List

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
object HomeDataUtils {
    fun findNum(homeBean: HomeBean): ArrayList<HomeBean.ResultsBean.BookingListBean> {
        val arrayList = ArrayList<String>()
        val bookingListBeans = ArrayList<HomeBean.ResultsBean.BookingListBean>()
        for (i in 0 until homeBean.results!!.bookingList!!.size) {
            arrayList.add(Integer.parseInt(homeBean.results!!.bookingList!!.get(i).bookingTime).toString() + "")
        }
        bookingListBeans.addAll(homeBean!!.results!!.bookingList as ArrayList<HomeBean.ResultsBean.BookingListBean>)

        return getNum(bookingListBeans, arrayList, homeBean!!.results!!.subclinicInfo!!.subclinicStartTime as String,
                homeBean!!.results!!.subclinicInfo!!.subclinicEndTime as String)
    }

    private fun getNum(datas: ArrayList<HomeBean.ResultsBean.BookingListBean>,
                       arrayList: ArrayList<String>,
                       staTime: String,
                       endIme: String): ArrayList<HomeBean.ResultsBean.BookingListBean> {
        val num = ArrayList<String>()
        for (i in 0..23) {
            num.add(i.toString() + "")
        }
        for (i in num.indices) {
            if (Integer.parseInt(staTime) <= i && !arrayList.contains(num[i]) && Integer.parseInt(endIme) >= i) {
                val bookingListBean = HomeBean.ResultsBean.BookingListBean(num[i] + "")
                datas.add(bookingListBean)
            }
        }

        Collections.sort(datas, Comparator<HomeBean.ResultsBean.BookingListBean> { p1, p2 ->
            //按照Person的年龄进行升序排列
            if (Integer.parseInt(p1.bookingTime) > Integer.parseInt(p2.bookingTime)) {
                return@Comparator 1
            }
            if (Integer.parseInt(p1.bookingTime) == Integer.parseInt(p2.bookingTime)) {
                0
            } else -1
        })
        var intNum = "-1"
        for (i in datas.indices) {
            if (i == 0) {
                datas[i].isShow=true
                intNum = datas[i].bookingTime as String
            } else {
                if (intNum == datas[i].bookingTime) {
                    datas[i].isShow=false
                } else {
                    datas[i].isShow=true
                    intNum = datas[i].bookingTime as String
                }
            }
        }
        return datas
    }
}
