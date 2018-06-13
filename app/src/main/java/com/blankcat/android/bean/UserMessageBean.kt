package com.blankcat.android.bean

import com.blankcat.android.bean.base.BaseBean

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: 用户信息
 */
class UserMessageBean:BaseBean(){
    /**
     * results : {"userInfo":{"userId":1,"userNo":"GYT000001","userName":"hello","userPhone":"1001","userPwd":"e10adc3949ba59abbe56e057f20f883e","userGender":1,"userImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg","userLevel":1,"subclinicId":1,"userRole":1,"userStatus":0,"leaveTime":null,"createTime":1519697041000,"subclinicName":"大望路店","userSource":null},"score":100,"gradeDoctor":{"gradeId":1,"gradeName":"初入江湖","gradeImg":null,"gradeStartScore":0,"gradeEndScore":100,"gradeNumber":1,"createTime":1519696982000}}
     */
    var  results:ResultsBean?=null
    class ResultsBean {
        /**
         * userInfo : {"userId":1,"userNo":"GYT000001","userName":"hello","userPhone":"1001","userPwd":"e10adc3949ba59abbe56e057f20f883e","userGender":1,"userImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg","userLevel":1,"subclinicId":1,"userRole":1,"userStatus":0,"leaveTime":null,"createTime":1519697041000,"subclinicName":"大望路店","userSource":null}
         * score : 100
         * gradeDoctor : {"gradeId":1,"gradeName":"初入江湖","gradeImg":null,"gradeStartScore":0,"gradeEndScore":100,"gradeNumber":1,"createTime":1519696982000}
         */
        var  userInfo:UserInfoBean?=null
        var  gradeName:String?=null
        class UserInfoBean {
            /**
             * userId : 1
             * userNo : GYT000001
             * userName : hello
             * userPhone : 1001
             * userPwd : e10adc3949ba59abbe56e057f20f883e
             * userGender : 1
             * userImg : https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg
             * userLevel : 1
             * subclinicId : 1
             * userRole : 1
             * userStatus : 0
             * leaveTime : null
             * createTime : 1519697041000
             * subclinicName : 大望路店
             * userSource : null
             */
            var  userNo:String?=null
            var  userName:String?=null
            var  userPhone:String?=null
            var  userPwd:String?=null
            var  userImg:String?=null
            var  subclinicName:String?=null
            var  userId:Int?=null
            var  userGender:Int?=null
            var  userLevel:Int?=null
            var  subclinicId:Int?=null
            var  userRole:Int?=null
            var  userStatus:Int?=null
            var  leaveTime:Any?=null
            var  createTime:Long?=null
            var  userSource:Any?=null
        }
    }
}