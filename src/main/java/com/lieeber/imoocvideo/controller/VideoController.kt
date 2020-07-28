package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.Constants.ffmpegPath
import com.lieeber.imoocvideo.Constants.rootPath
import com.lieeber.imoocvideo.enums.VideoStatusEnum
import com.lieeber.imoocvideo.pojo.Videos
import com.lieeber.imoocvideo.service.BgmService
import com.lieeber.imoocvideo.service.VideoService
import com.lieeber.imoocvideo.utils.FetchVideoCover
import com.lieeber.imoocvideo.utils.IMoocJSONResult
import com.lieeber.imoocvideo.utils.MergeVideoMp3
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*

@RestController
@RequestMapping("video")
class VideoController : BasicController() {

    @Autowired
    lateinit var videoService: VideoService

    @Autowired
    lateinit var bgmService: BgmService

    @PostMapping("/upload")
    fun uploadVideo(userToken: String?, bgmId: String?,
                    videoWidth: Int?, videoHeight: Int?, desc: String?,
                    duration: Float,
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
        val videoFileName = file.originalFilename
        val videoRelativePath = File.separator + "${userId}${File.separator}userVideo${File.separator}"
        val videoFileAbsolutePath = rootPath + videoRelativePath + videoFileName
        var fileOutputStream: FileOutputStream? = null
        try {
            val outFile = File(videoFileAbsolutePath)
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

        //如果选择了背景音乐，就将背景音乐和当前视频合成一个视频
        if (!bgmId.isNullOrEmpty()) {
            val bgm = bgmService.queryBgmById(bgmId)
            if (bgm != null) {  //如果查询到的bgm不为空，就将视频和音频合并
                val ffmpeg = MergeVideoMp3(ffmpegPath)
                ffmpeg.convertor(videoFileAbsolutePath, rootPath + bgm.path,
                        rootPath + videoRelativePath + UUID.randomUUID() + ".mp4")
            }
        }

        //生成视频封面图
        val coverFileName = videoFileName!!.split(".")[0] + ".jpg"
        val coverFileAbsolutePath = rootPath + videoRelativePath + coverFileName
        val fetchVideoCover = FetchVideoCover(ffmpegPath)
        fetchVideoCover.getCover(videoFileAbsolutePath, coverFileAbsolutePath)
        val videos = Videos()
        videos.audioId = bgmId
        videos.userId = userId
        videos.status = VideoStatusEnum.SUCCESS.ordinal + 1
        videos.videoHeight = videoHeight
        videos.videoWidth = videoWidth
        videos.createTime = Date()
        videos.videoSeconds = duration.toFloat()
        videos.videoPath = videoRelativePath + videoFileName
        videos.coverPath = videoRelativePath + coverFileName
        videos.videoDesc = desc
        videoService.saveVideo(videos)
        return IMoocJSONResult.ok(videoRelativePath + videoFileName)
    }


    @GetMapping("/showAll")
    fun showAllVideos(@RequestParam("page", defaultValue = "1") page: Int,
                      @RequestParam("pageSize", defaultValue = "10") pageSize: Int): IMoocJSONResult {
        println("" + page + "::" + pageSize)
        val allVideos = videoService.getAllVideos(page, pageSize)
        return IMoocJSONResult.ok(allVideos)
    }

//    @GetMapping("")
}