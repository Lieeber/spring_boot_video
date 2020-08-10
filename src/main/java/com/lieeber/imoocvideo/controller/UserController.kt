package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.Constants.rootPath
import com.lieeber.imoocvideo.pojo.Users
import com.lieeber.imoocvideo.pojo.vo.UsersVO
import com.lieeber.imoocvideo.service.UserService
import com.lieeber.imoocvideo.utils.UnifyResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.commons.io.IOUtils
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

@Api(value = "用户相关业务的接口", tags = ["用户相关业务的controller"])
@RestController
@RequestMapping("user")
class UserController : BasicController() {

    @Autowired
    lateinit var userService: UserService

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")
    @ApiImplicitParam(name = "userToken", value = "用户token", required = true, dataType = "String", paramType = "query")
    @PostMapping("/upload_avatar", headers = ["content-type=multipart/form-data"])
    fun uploadAvatar(@RequestParam userToken: String?, @ApiParam(name = "file", value = "file", required = true) @RequestParam("file") files: Array<MultipartFile>): UnifyResponse {
        if (userToken.isNullOrBlank()) {
            return UnifyResponse.errorMsg("用户没有登录，请重新登录")
        }
        val userId = redis?.get("$USER_REDIS_SESSION:$userToken")
        if (userId.isNullOrBlank()) {
            return UnifyResponse.errorMsg("用户登录已过期，请重新登录")
        }
        if (files.isEmpty()) {
            return UnifyResponse.errorMsg("图片上传失败，请重新上传")
        }
        val file = files[0]
        val fileName = file.originalFilename
        val path = "/${userId}/userAvatar/"
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
            return UnifyResponse.errorMsg("上传出错...")
        } finally {
            fileOutputStream?.flush()
            fileOutputStream?.close()
        }
        //将用户上传的图片路径保存到数据库
        val user = Users()
        user.id = userId
        user.faceImage = dbPath
        userService.updateUserInfo(user)
        return UnifyResponse.ok(dbPath)
    }


    @GetMapping("/query")
    fun getUserInfo(@CookieValue(value = USER_REDIS_SESSION, defaultValue = "") userToken: String?, userId: String?): UnifyResponse {
        if (userId.isNullOrBlank()) {
            return UnifyResponse.errorMsg("用户id不能为空")
        }
        val user = userService.queryUserInfo(userId)
        if (user == null) {
            return UnifyResponse.errorMsg("该用户不存在，请确认userId是否正确。")
        }
        var userVO = UsersVO()
        BeanUtils.copyProperties(user, userVO)
        //如果该用户是自己，还需要带上userToken
        if (!userToken.isNullOrEmpty()) {
            val myUserId = redis?.get("$USER_REDIS_SESSION:$userToken")
            if (myUserId == userId) {
                userVO.userToken = userToken
            }
        }
        return UnifyResponse.ok(userVO)
    }
}
