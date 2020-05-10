package com.gentlehu.qrcodelogin.tools

import java.util.*

class Tools {
    companion object{
        fun uuid():String{
            return UUID.randomUUID().toString()
        }
    }
}