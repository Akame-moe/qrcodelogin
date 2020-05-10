package com.gentlehu.qrcodelogin.controller

import com.gentlehu.qrcodelogin.Database
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.collections.Map

@RestController
class PollController {

    @GetMapping("/query")
    fun query(uid:String): Map<String,Any> {
        val info = Database.get(uid)
        val token = UUID.randomUUID().toString()
        // put token into session pool
        return if(info == null){
            mapOf("code" to 300)
        } else{
            mapOf("code" to 200,"data" to info,"token" to token)
        }
    }
}