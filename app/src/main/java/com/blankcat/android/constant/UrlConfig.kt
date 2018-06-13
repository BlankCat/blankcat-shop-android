package com.blankcat.android.constant

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: 用于配置网络连接地址以及参数名称
 */
public object UrlConfig {
    /**
     * 用于写最基础的url，主要是用于生产环境和开发环境替换
     */
    const val BASE_URL = "http://10.2.3.208:18080/"
    const val BASE_URL_SECOND_LEVEL = "/xrtn-doctor/"
    /**主页*/
    const val HOME = BASE_URL_SECOND_LEVEL + "{version}/index/desktop.json"
    /**个人中心*/
    const val SELECTUSERINFO = BASE_URL_SECOND_LEVEL + "{version}/user/selectUserInfo.json"
}