package com.blankcat.android.ui.personalCenter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.blankcat.android.R
import com.blankcat.android.base.BaseActivity
import com.blankcat.android.bean.UserMessageBean
import com.blankcat.android.constant.AppConstants
import com.blankcat.android.customView.CircleImageViewTwo
import com.blankcat.android.mvp.MVPContract
import com.blankcat.android.mvp.presenter.UserMessageInfoPresenter
import com.blankcat.android.utils.ImageUtils
import com.blankcat.android.utils.SPUtils
import java.util.*

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: 个人中心页面
 */
public class PersonalCenterActivity : BaseActivity(),
                                View.OnClickListener,
                                MVPContract.IUserInfoMessageView
{
    internal var backIv: ImageView? = null
    internal var titleNameTv: TextView? = null
    internal var titleRightTv: TextView? = null
    internal var titleRightIv: ImageView? = null
    internal var headIv: CircleImageViewTwo? = null
    internal var nameTv: TextView? = null
    internal var messageTv: TextView? = null
    internal var tvMyScore: TextView? = null
    internal var tvLeaveManager: TextView? = null
    internal var tvMyOrder: TextView? = null

    var userInfoMessagePresenter:MVPContract.IUserInfoMessagePresenter?=null

    private val times = ArrayList<String>()
    private val flags = ArrayList<Int>()


    override fun getLayoutId(): Int {
        return R.layout.activity_personal_center
    }

    override fun initPresenter() {
        if (null==userInfoMessagePresenter){
            userInfoMessagePresenter = UserMessageInfoPresenter()
            userInfoMessagePresenter!!.attachView(this)
        }
    }

    override fun initViews() {
        this.backIv = findViewById(R.id.back_iv)
        this.titleNameTv = findViewById(R.id.title_name_tv)
        this.titleRightTv = findViewById(R.id.title_right_tv)
        this.titleRightIv = findViewById(R.id.title_right_iv)
        this.headIv = findViewById(R.id.head_iv)
        this.nameTv = findViewById(R.id.name_tv)
        this.messageTv = findViewById(R.id.message_tv)
        this.tvMyScore = findViewById(R.id.tv_my_score)
        this.tvLeaveManager = findViewById(R.id.tv_leave_manager)
        this.tvMyOrder = findViewById(R.id.tv_my_order)
    }

    override fun setListener() {
        backIv!!.setOnClickListener(this)
        titleRightTv!!.setOnClickListener(this)
        titleRightIv!!.setOnClickListener(this)
        headIv!!.setOnClickListener(this)
        nameTv!!.setOnClickListener(this)
        messageTv!!.setOnClickListener(this)
        tvMyScore!!.setOnClickListener(this)
        tvLeaveManager!!.setOnClickListener(this)
        tvMyOrder!!.setOnClickListener(this)
    }

    override fun initData() {
        titleNameTv!!.setText(R.string.my)
        val map = HashMap<String, String>()
        val doctorId = SPUtils.get(AppConstants.SP_USER_ID, -1) as Int
        map.put("doctorId", doctorId.toString() + "")
        userInfoMessagePresenter!!.loadUserInfoMessage(this, map)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.back_iv->{
                Toast.makeText(this,"back_iv",Toast.LENGTH_SHORT).show()
            }
            R.id.title_right_tv->{
                Toast.makeText(this,"title_right_tv",Toast.LENGTH_SHORT).show()
            }
            R.id.title_right_iv->{
                Toast.makeText(this,"title_right_iv",Toast.LENGTH_SHORT).show()
            }
            R.id.head_iv->{
                 Toast.makeText(this,"head_iv",Toast.LENGTH_SHORT).show()
            }
             R.id.name_tv->{
                 Toast.makeText(this,"name_tv",Toast.LENGTH_SHORT).show()
            }
            R.id.message_tv->{
                Toast.makeText(this,"message_tv",Toast.LENGTH_SHORT).show()
            }
            R.id.tv_my_score->{
                Toast.makeText(this,"tv_my_score",Toast.LENGTH_SHORT).show()
            }
            R.id.tv_leave_manager->{
                Toast.makeText(this,"tv_leave_manager",Toast.LENGTH_SHORT).show()
            }
            R.id.tv_my_order->{
                Toast.makeText(this,"tv_my_order",Toast.LENGTH_SHORT).show()
            }
            R.id.tv_set->{
                Toast.makeText(this,"tv_set",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onUserInfoMessageSuccess(userMessageBean: UserMessageBean) {
        nameTv!!.setText(userMessageBean.results!!.userInfo!!.userName)
        ImageUtils.showImage(this, userMessageBean.results!!.userInfo!!.userImg as String, headIv as ImageView)
        messageTv!!.setText(userMessageBean!!.results!!.gradeName)
    }

    override fun onFailed(msg: String) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show()
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
        if (null != userInfoMessagePresenter) {
            userInfoMessagePresenter!!.detachView()
        }
    }


}
