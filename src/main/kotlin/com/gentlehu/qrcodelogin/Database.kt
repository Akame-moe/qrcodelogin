package com.gentlehu.qrcodelogin

import com.gentlehu.qrcodelogin.pojo.UserInfo
import java.util.concurrent.atomic.AtomicInteger

class Database {

    companion object{
        private val map = mutableMapOf<String,UserInfo>()

        fun put(k:String,v:UserInfo){
            map[k] = v
        }
        fun get(k: String):UserInfo?{
            return map[k]
        }

        private val onlineCount = AtomicInteger(0)

        fun addOnlineCount() {
            onlineCount.incrementAndGet()
        }

        fun subOnlineCount() {
            onlineCount.decrementAndGet()
        }
        fun count():Int{
            return onlineCount.get()
        }
    }

}