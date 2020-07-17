@file:JvmName("Users")

package com.lieeber.imoocvideo.pojo

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import lombok.Data
import javax.persistence.Column
import javax.persistence.Id

class UsersVO {
    var username: String? = null
    var password: String? = null
    var userToken: String? = null
    var faceImage: String? = null
    var nickname: String? = null
    var fansCounts: Int? = null
    var followCounts: Int? = null
    var receiveLikeCounts: Int? = null
}