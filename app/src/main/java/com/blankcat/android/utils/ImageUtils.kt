package com.blankcat.android.utils

import android.content.Context
import android.widget.ImageView

import com.blankcat.android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions

import java.io.File

/**
 * 图片加载工具类
 */
  object ImageUtils {

    fun showImage( context:Context,  url:String,  view:ImageView) {
        if (!SPUtils.isNoString(url)) {
           var url = url.replace(":443", ":80")
            var options =  RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.head)
                            .error(R.mipmap.head)
                            .priority(Priority.HIGH)
            Glide.with(context).load(url).apply(options).into(view)
        } else {
            Glide.with(context).load(R.mipmap.head).into(view)
        }
    }


    fun showImage( context:Context,  url:Int,  view:ImageView) {
        var options =  RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.head)
                        .error(R.mipmap.head)
                        .priority(Priority.HIGH)
        Glide.with(context).load(url).apply(options).into(view)
    }

    fun showImage( context:Context,  file:File,  view:ImageView) {
        var options =  RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.head)
                        .error(R.mipmap.head)
                        .priority(Priority.HIGH)

        Glide.with(context).load(file).apply(options).into(view)
    }
}
