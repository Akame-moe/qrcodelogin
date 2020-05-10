package com.gentlehu.qrcodelogin

import com.gentlehu.qrcodelogin.pojo.UserInfo

class SessionPool {
    companion object{
        private val map = mutableMapOf<String,UserInfo>()

        fun put(token:String,ui:UserInfo){
            println("SessionPool:put->$ui")
            map.put(token,ui)
        }
        fun get(token:String):UserInfo?{
            return map.get(token)
        }
        fun remove(token: String){
            map.remove(token)
        }
    }
}