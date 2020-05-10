package com.gentlehu.qrcodelogin.controller

import com.gentlehu.qrcodelogin.SessionPool
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest

@Controller
class HtmlController {

    @GetMapping("/")
    fun index():String{
        return "login.html"
    }
    @GetMapping("/profile")
    fun profile(model: Model,req:HttpServletRequest):String{
        model.addAttribute("msg","this is your profile")
        var t:String = ""
        for (c in req.cookies){
            if (c.name == "token"){
                t = c.value
                break
            }
        }
        println("t:$t###")
        val ui = SessionPool.get(t)
        println("ui:$ui")
        model.addAttribute("u",ui?.name)
        model.addAttribute("bio",ui?.bio)
        return "profile.html"
    }

    @GetMapping("/af")
    fun authorizeForm(): String {
        return "af.html"
    }

}