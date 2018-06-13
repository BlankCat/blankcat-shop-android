package com.blankcat.android.base
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import butterknife.ButterKnife
import com.blankcat.android.MainActivity
import com.blankcat.android.R
import com.blankcat.android.constant.AppConstants
import com.blankcat.android.utils.AppStatusTrackerUtils
import com.blankcat.android.utils.StatusBarCompatUtils


/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
 abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var window = this.getWindow()
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        StatusBarCompatUtils.compat(this, ContextCompat.getColor(this, R.color.colorAccent))
        var  actionBar = getSupportActionBar() as ActionBar
        actionBar.hide()
        if (0 != getLayoutId()) {
            setContentView(getLayoutId())
            ButterKnife.bind(this)
            when (AppStatusTrackerUtils.getInstance().getAppStatus()) {
                 AppConstants.STATUS_FORCE_KILLED ->
                 protectApp()
                 AppConstants.STATUS_KICK_OUT,
                 AppConstants.STATUS_LOGOUT,
                 AppConstants.STATUS_OFFLINE,
                 AppConstants.STATUS_ONLINE-> {
                   init()
                 }
            }
        } else {
            init()
        }
    }
    override fun getResources(): Resources {
        var res = super.getResources()
        var config =  Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.getDisplayMetrics())
        return res
    }

     fun protectApp() {
        var intent =  Intent(this, MainActivity::class.java)
        intent.putExtra("action", "force_kill")
        startActivity(intent)
    }

    /**
     * 伴随对象用于对外提供静态方法
     */
     companion object {
        open fun  IsEmptyOrNullString(s:String) : Boolean{
            return (s == null) || (s.trim().length == 0)
        }
    }

    private  fun init(){
        setListener()
        initPresenter()
        initViews()
        initData()
    }
    abstract fun getLayoutId():Int
    abstract fun initViews()
    abstract fun setListener()
    abstract fun initPresenter()
    abstract fun initData()

    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }


    lateinit var  loadingDialog:AlertDialog
    fun showLoadingDialog():AlertDialog {
        loadingDialog =  AlertDialog.Builder(this).create()
        loadingDialog.getWindow().setBackgroundDrawable( ColorDrawable())
        loadingDialog.setCancelable(false)
        loadingDialog.setOnKeyListener(DialogInterface.OnKeyListener { v, keyCode, event ->
            Log.e("tag", "keyCode$keyCode, ${event.keyCode}")
            if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                return@OnKeyListener true
            return@OnKeyListener false
        })
        loadingDialog.show()
        loadingDialog.setContentView(R.layout.dialog_loadding)
        loadingDialog.setCanceledOnTouchOutside(false)
        return loadingDialog
    }

    fun dismissLoadingDialog() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss()
        }
    }

}
