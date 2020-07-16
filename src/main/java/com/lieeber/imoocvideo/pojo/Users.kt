@file:JvmName("Users")
package com.lieeber.imoocvideo.pojo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import javax.persistence.Column
import javax.persistence.Id

@ApiModel(value = "用户对象", description = "这是用户对象")
class Users {
    @ApiModelProperty(hidden = true)
    @Id
    var id: String? = null

    @ApiModelProperty(value = "用户名", name = "username", example = "imoocuser", required = true)
    var username: String? = null

    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    var password: String? = null

    @ApiModelProperty(hidden = true)
    @Column(name = "face_image")
    var faceImage: String? = null

    @ApiModelProperty(hidden = true)
    var nickname: String? = null

    @ApiModelProperty(hidden = true)
    @Column(name = "fans_counts")
    var fansCounts: Int? = null

    @ApiModelProperty(hidden = true)
    @Column(name = "follow_counts")
    var followCounts: Int? = null

    @ApiModelProperty(hidden = true)
    @Column(name = "receive_like_counts")
    var receiveLikeCounts: Int? = null
}