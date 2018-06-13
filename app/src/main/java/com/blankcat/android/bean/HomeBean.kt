package com.blankcat.android.bean

import java.util.List
import com.blankcat.android.bean.base.BaseBean

/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
class HomeBean : BaseBean() {
    /**
     * results : {"userInfo":{"userId":1,"userName":"hello","userPhone":"1001","userPwd":"e10adc3949ba59abbe56e057f20f883e","userGender":1,"userImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg","userLevel":1,"subclinicId":1,"userRole":1,"userStatus":0,"leaveTime":null,"createTime":1519697041000,"subclinicName":"大望路店"},"bookingList":[{"isFirst":0,"bookingTime":"09","userImg":"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&ie=utf-8&in=24401&cl=2&lm=-1&st=-1&step_word=&rn=1&cs=&ln=1998&fmq=1402900904181_R&ic=0&s=&se=1&sme=0&tab=&width=&height=","bookingStatus":0,"userName":"一叶知秋","userId":1},{"isFirst":1,"bookingTime":"10","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=2036166846,30","bookingStatus":1,"userName":"长林","userId":2},{"isFirst":0,"bookingTime":"17","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=4200696989,89","bookingStatus":2,"userName":"林夕","userId":3},{"isFirst":0,"bookingTime":"17","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3614240102,29","bookingStatus":3,"userName":"长巾","userId":4},{"isFirst":1,"bookingTime":"19","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=8936967,20762","bookingStatus":4,"userName":"风云","userId":5}],"subclinicInfo":{"subclinicId":1,"subclinicName":"大望路店","subclinicLogo":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%A4%","subclinicPhone":null,"subclinicStartTime":"10","subclinicEndTime":"22","subclinicAddress":null,"subclinicLatLon":null,"subclinicDesc":null,"subclinicStatus":null,"createTime":null}}
     */
    var results:ResultsBean?=null

      class ResultsBean {
        /**
         * userInfo : {"userId":1,"userName":"hello","userPhone":"1001","userPwd":"e10adc3949ba59abbe56e057f20f883e","userGender":1,"userImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2043305675,3979419376&fm=200&gp=0.jpg","userLevel":1,"subclinicId":1,"userRole":1,"userStatus":0,"leaveTime":null,"createTime":1519697041000,"subclinicName":"大望路店"}
         * bookingList : [{"isFirst":0,"bookingTime":"09","userImg":"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&ie=utf-8&in=24401&cl=2&lm=-1&st=-1&step_word=&rn=1&cs=&ln=1998&fmq=1402900904181_R&ic=0&s=&se=1&sme=0&tab=&width=&height=","bookingStatus":0,"userName":"一叶知秋","userId":1},{"isFirst":1,"bookingTime":"10","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=2036166846,30","bookingStatus":1,"userName":"长林","userId":2},{"isFirst":0,"bookingTime":"17","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=4200696989,89","bookingStatus":2,"userName":"林夕","userId":3},{"isFirst":0,"bookingTime":"17","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3614240102,29","bookingStatus":3,"userName":"长巾","userId":4},{"isFirst":1,"bookingTime":"19","userImg":"https://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E7%BE%8E%E5%A5%B3%20%E4%BD%A0%E5%A5%BD%E6%BC%82%E4%BA%AE&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=8936967,20762","bookingStatus":4,"userName":"风云","userId":5}]
         * subclinicInfo : {"subclinicId":1,"subclinicName":"大望路店","subclinicLogo":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%A4%","subclinicPhone":null,"subclinicStartTime":"10","subclinicEndTime":"22","subclinicAddress":null,"subclinicLatLon":null,"subclinicDesc":null,"subclinicStatus":null,"createTime":null}
         */

        var userInfo:UserInfoBean?=null
        var subclinicInfo:SubclinicInfoBean?=null
        var  bookingList: List<BookingListBean>?=null

         class UserInfoBean {
            /**
             * userId : 1
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
             */
            var  userId:Int?=null
            var  userName:String?=null
            var userPhone:String?=null
            var userPwd:String?=null
            var userGender:String?=null
            var userImg:String?=null
            var userLevel:Int?=null
            var subclinicId:Int?=null
            var userRole:Int?=null
            var userStatus:Int?=null
            var leaveTime:Any?=null
            var createTime:Long?=null
            var subclinicName:String?=null

        }

         class SubclinicInfoBean {
            /**
             * subclinicId : 1
             * subclinicName : 大望路店
             * subclinicLogo : http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%A4%
             * subclinicPhone : null
             * subclinicStartTime : 10
             * subclinicEndTime : 22
             * subclinicAddress : null
             * subclinicLatLon : null
             * subclinicDesc : null
             * subclinicStatus : null
             * createTime : null
             */
            var subclinicStartTime:String?=null
            var  subclinicEndTime:String?=null
        }

         class BookingListBean {
            constructor(bookingTime:String){
                this.bookingTime = bookingTime
            }
            /**
             * isFirst : 0
             * bookingTime : 09
             * userImg : http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&ie=utf-8&in=24401&cl=2&lm=-1&st=-1&step_word=&rn=1&cs=&ln=1998&fmq=1402900904181_R&ic=0&s=&se=1&sme=0&tab=&width=&height=
             * bookingStatus : 0
             * userName : 一叶知秋
             * userId : 1
             */

            var  isShow:Boolean?=null
            var isFirst:Int?=null
            var userLevel:Int?=null
            var bookingTime:String?=null
            var userImg:String?=null
            var bookingStatus:Int?=null
            var patientId:Int?=null
            var userName:String?=null
            var userPhone:String?=null
            var userId:String?=null
            var bookingId:Int?=null
        }
    }
}
