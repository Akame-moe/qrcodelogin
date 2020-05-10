package com.gentlehu.qrcodelogin.controller

import com.gentlehu.qrcodelogin.SessionPool
import com.gentlehu.qrcodelogin.WebSocketSessionPool
import com.gentlehu.qrcodelogin.pojo.UserInfo
import com.gentlehu.qrcodelogin.tools.Tools
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/web")
class WebSocketController {

    @GetMapping("/authorize")
    fun authorize(wid:String,u:String,bio:String):String{
        //效验从手机端传来的信息，这里简单起见就不效验了，传来了u,bio参数就行了
        //通知浏览器授权完成，进行跳转
        println("receive:#$wid,$u,$bio")
        var token = Tools.uuid()
        SessionPool.put(token, UserInfo(1,u,bio))
        val wse = WebSocketSessionPool.get(wid)
        if (wse != null && wse.isOpen()){
            wse.sendMessage("""{"state":"complete","u":"$u","bio":"$bio","token":"$token"}""")
            wse.close()
            return "authorization complete."
        }else{
            return "QRCode expired."
        }


    }


}