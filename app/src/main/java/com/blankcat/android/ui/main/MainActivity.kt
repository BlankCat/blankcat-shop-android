package com.blankcat.android.ui.main;

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ViewUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.blankcat.android.R
import com.blankcat.android.base.BaseActivity
import com.blankcat.android.bean.HomeBean
import com.blankcat.android.constant.AppConstants
import com.blankcat.android.customView.RoundImageView
import com.blankcat.android.mvp.MVPContract
import com.blankcat.android.mvp.presenter.HomePresenter
import com.blankcat.android.utils.HomeDataUtils
import com.blankcat.android.utils.ImageUtils
import com.blankcat.android.utils.SPUtils
import java.util.*

class MainActivity : BaseActivity() ,
                    View.OnClickListener,
                    MVPContract.IHomeView,
                    SwipeRefreshLayout.OnRefreshListener
{
     lateinit var backIv: ImageView
     lateinit var titleNameTv: TextView
     lateinit var headIv: RoundImageView
     lateinit var nameTv: TextView
     lateinit var storeNameTv: TextView
     lateinit var messageTv: TextView
     lateinit var timeTv: TextView
     lateinit var homeRl: RecyclerView
     lateinit var swipeRefreshLayout: SwipeRefreshLayout


    var homePresenter:MVPContract.IHomePresenter?=null
     var mainAdapter:MainAdapter?=null
     val datas = ArrayList<HomeBean.ResultsBean.BookingListBean>()
     override fun getLayoutId(): Int {
         return R.layout.activity_main
     }

     override fun initPresenter() {
         if (null == homePresenter) {
             homePresenter = HomePresenter()
             homePresenter!!.attachView(this)
         }
     }

     override fun initViews() {
         this.backIv = findViewById(R.id.back_iv)
         this.titleNameTv = findViewById(R.id.title_name_tv)
         this.headIv = findViewById(R.id.head_iv)
         this.nameTv = findViewById(R.id.name_tv)
         this.storeNameTv = findViewById(R.id.store_name_tv)
         this.messageTv = findViewById(R.id.message_tv)
         this.timeTv = findViewById(R.id.time_tv1)
         this.homeRl = findViewById(R.id.home_rl)
         this.swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
     }


     override fun setListener() {
         backIv.setOnClickListener(this)
         timeTv.setOnClickListener(this)
         swipeRefreshLayout.setOnRefreshListener(this)
     }

     override fun initData() {
//         val userId = SPUtils.get(AppConstants.SP_USER_ID, -1) as Int
         swipeRefreshLayout.setColorSchemeResources(R.color.title_color, R.color.title_color, R.color.title_color)

         homePresenter!!.loadHome(this, timeTv.text.toString().replace(".", "-"),  "")

         homeRl.layoutManager = LinearLayoutManager(this)
         mainAdapter = MainAdapter(datas)
         homeRl.adapter = mainAdapter

         /**
          *设置点击事件
          */
         val itemOnClick: (View, Int) -> Unit = { view, position ->
             Toast.makeText(this@MainActivity, "未到就诊时间"+position, Toast.LENGTH_SHORT).show()
        }
        mainAdapter!!.setOnItemClickListenerByLambda(
                itemOnClick
        )
     }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back_iv->{
                Toast.makeText(this,"back",Toast.LENGTH_SHORT).show()
            }
            R.id.time_tv1->{
                Toast.makeText(this,"time_tv1",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSuccess(homeBean: HomeBean) {
        setDataView(homeBean)
        swipeRefreshLayout.isRefreshing = false
        datas.clear()
        datas.addAll(HomeDataUtils.findNum(homeBean))
        mainAdapter!!.notifyDataSetChanged()
    }

    override fun onFailed(msg: String) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show()
    }

    private fun setDataView(homeBean: HomeBean) {
        val resultsBean = homeBean.results
        val userInfoBean = resultsBean!!.userInfo
        ImageUtils.showImage(this, userInfoBean!!.userImg as String, headIv)
        nameTv.setText(userInfoBean!!.userName)
        storeNameTv.setText(userInfoBean.subclinicName)
    }

    /**
     * 下拉刷新
     */
    override fun onRefresh() {
        if (null != loadingDialog) {
            dismissLoadingDialog()
        }
        loadingDialog = showLoadingDialog()
        if (null == homePresenter) {
            homePresenter = HomePresenter()
            homePresenter!!.attachView(this)
        }
        val userId = SPUtils.get(AppConstants.SP_USER_ID, -1) as Int
        homePresenter!!.loadHome(this, timeTv.text.toString().replace(".", "-"), userId.toString() + "")
    }

    override fun showloadingDialog() {
        if (null != loadingDialog) {
            dismissLoadingDialog()
        }
        loadingDialog = showLoadingDialog()
    }


    override fun hideLoadingDialog() {
        if (null != loadingDialog)
            dismissLoadingDialog()
    }

     override fun onDestroy() {
         super.onDestroy()
         if (null != homePresenter) {
             homePresenter!!.detachView()
         }
     }
 }
