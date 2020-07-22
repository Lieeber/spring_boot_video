package com.lieeber.imoocvideo

import java.io.File


object Constants {
      val rootPath:String
        get() {
            val osName = System.getProperty("os.name")
            if (osName.startsWith("Windows")) {
                println(osName)
                return "c:\\Users\\lieeb\\Downloads\\imoocvideo_save"
            } else {
                return "/Users/lieeber/Downloads/imoocvideo_save"
            }
        }
}