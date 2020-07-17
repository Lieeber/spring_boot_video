package com.lieeber.imoocvideo.controller

import com.lieeber.imoocvideo.utils.IMoocJSONResult
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Api(value = "用户相关业务的接口", tags = ["用户相关业务的controller"])
@RestController
@RequestMapping("user")
class UserController : BasicController() {

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")
    @ApiImplicitParam(name = "userToken", value = "用户token", required = true, dataType = "String", paramType = "query")
    @PostMapping("/upload_avatar",headers= ["content-type=multipart/form-data"])
    fun uploadAvatar(@RequestParam userToken: String?,
                     @ApiParam(name = "file",value = "file", required = true)
                     @RequestParam("file") files: Array<MultipartFile>) :IMoocJSONResult{
        if (userToken.isNullOrBlank()) {
            return IMoocJSONResult.errorMsg("用户没有登录，请重新登录")
        }
        return IMoocJSONResult.errorMsg("用户没有登录，请重新登录")
    }
}