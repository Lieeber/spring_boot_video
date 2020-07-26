package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.Constants.ffmpegPath
import com.lieeber.imoocvideo.Constants.rootPath
import com.lieeber.imoocvideo.pojo.Videos
import com.lieeber.imoocvideo.service.BgmService
import com.lieeber.imoocvideo.service.VideoService
import com.lieeber.imoocvideo.utils.IMoocJSONResult
import com.lieeber.imoocvideo.utils.MergeVideoMp3
import org.apache.commons.io.IOUtils
import org.n3r.idworker.Sid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

@RestController
@RequestMapping("video")
class VideoController : BasicController() {

    @Autowired
    lateinit var videoService: VideoService

    @Autowired
    lateinit var bgmService: BgmService

    @Autowired
    lateinit var sid: Sid

    @PostMapping("/upload")
    fun uploadVideo(userToken: String?, bgmId: String?,
                    videoWidth: Int?, videoHeight: Int?, desc: String?,
                    @RequestParam("file") file: MultipartFile?): IMoocJSONResult {
        if (userToken.isNullOrBlank()) {
            return IMoocJSONResult.errorMsg("用户没有登录，请重新登录")
        }
        val userId = redis?.get("${BasicController.USER_REDIS_SESSION}:$userToken")
        if (userId.isNullOrBlank()) {
            return IMoocJSONResult.errorMsg("用户登录已过期，请重新登录")
        }
        if (file == null) {
            return IMoocJSONResult.errorMsg("视频上传失败，请重新上传")
        }
        val fileName = file.originalFilename
        val path = File.separator+"${userId}${File.separator}userVideo${File.separator}"
        val filePath = rootPath + path + fileName
        val dbPath = path + fileName
        var fileOutputStream: FileOutputStream? = null
        try {
            val outFile = File(filePath)
            if (outFile.parentFile != null || !outFile.parentFile.isDirectory) {
                outFile.parentFile.mkdirs()
            }
            fileOutputStream = FileOutputStream(outFile)
            IOUtils.copy(file.inputStream, fileOutputStream)

        } catch (e: Exception) {
            return IMoocJSONResult.errorMsg("上传出错...")
        } finally {
            fileOutputStream?.flush()
            fileOutputStream?.close()
        }

        if (!bgmId.isNullOrEmpty()){
            val bgm = bgmService.queryBgmById(bgmId)
            if (bgm != null) {  //如果查询到的bgm不为空，就将视频和音频合并
                val videoPath = rootPath  + dbPath
                val audioPath = rootPath+ bgm.path
                val substring = fileName!!.substring(2)
                val endPath = rootPath + path + substring  //原视频和目标视频不能是同路径同名字
                val ffmpeg = MergeVideoMp3(ffmpegPath)
                ffmpeg.convertor(videoPath, audioPath, endPath)
            }
        }

        val videos = Videos()
        videos.id = sid.nextShort()
        videos.userId = userId
        videos.status = 1
        videos.videoPath = dbPath
        if (!bgmId.isNullOrBlank()) {
            videos.audioId = bgmId
        }
        //保存视频信息到数据库
        videoService.saveVideo(videos)
        return IMoocJSONResult.ok(dbPath)
    }
}