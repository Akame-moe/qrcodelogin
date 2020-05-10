package com.gentlehu.qrcodelogin

import com.gentlehu.qrcodelogin.controller.WebSocketEndpoint

class WebSocketSessionPool {
    companion object{
        private val map = mutableMapOf<String,WebSocketEndpoint>()
        //wid :WebSocket ID
        fun put(wid:String,wse:WebSocketEndpoint){
            map.put(wid,wse)
        }
        fun get(wid:String): WebSocketEndpoint? {
            return map.get(wid)
        }
        fun remove(wid:String){
            map.remove(wid)
        }
    }
}