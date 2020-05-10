package com.gentlehu.qrcodelogin.controller

import com.gentlehu.qrcodelogin.Database
import com.gentlehu.qrcodelogin.WebSocketSessionPool
import com.gentlehu.qrcodelogin.tools.Tools
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import javax.websocket.*
import javax.websocket.server.ServerEndpoint
import kotlin.random.Random


@ServerEndpoint("/websocket")
@Component
class WebSocketEndpoint {

    private lateinit var session: Session
    private lateinit var wid: String

    @OnOpen
    fun onOpen(session: Session) {
        this.session = session
        //IDLE最大时长，超过这个时长关闭session
        this.session.maxIdleTimeout = TimeUnit.SECONDS.toMillis(120L);
        Database.addOnlineCount()
        //暂时使用uuid作为随机字符串来区分WebSocket客户端，将来可以考虑使用更长的随机字符串
        val wid = Tools.uuid()
        this.wid = wid
        WebSocketSessionPool.put(wid,this)
        println("current online count ${Database.count()}")
        sendMessage("""{"state":"authorize","wid":"$wid"}""")
    }

    @OnClose
    fun onClose() {
        Database.subOnlineCount()
        WebSocketSessionPool.remove(this.wid)
        println(" current online ${Database.count()}")
    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        println("[${session.id}]:${message}")
    }

    @OnError
    fun onError(session: Session, error: Throwable) {
        println("A ERROR OCCUR.")
        error.printStackTrace()
    }

    @Throws(IOException::class)
    fun sendMessage(message: String) {
        session.basicRemote.sendText(message)

    }
    fun isOpen():Boolean{
        return session.isOpen
    }

    fun close(){
        session.close()
    }




}