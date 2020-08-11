package com.lieeber.imoocvideo

object Constants {
    val rootPath: String
        get() {
            val osName = System.getProperty("os.name")
            if (osName.startsWith("Windows")) {
                println(osName)
                return "c:\\Users\\lieeb\\Downloads\\imoocvideo_save"
            } else {
                return "/Users/lieeber/Downloads/imoocvideo_save"
            }
        }

    val ffmpegPath: String
        get() {
            val osName = System.getProperty("os.name")
            if (osName.startsWith("Windows")) {
                println(osName)
                return "C:\\ProgramData\\chocolatey\\bin\\ffmpeg.exe"
            } else {
                return "/usr/local/Cellar/ffmpeg/4.3_3/bin/ffmpeg"
            }
        }
}