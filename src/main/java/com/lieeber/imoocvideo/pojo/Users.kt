package com.lieeber.imoocvideo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@ApiModel(value = "用户对象", description = "这是用户对象")
@Data
public class Users {
    @ApiModelProperty(hidden = true)
    @Id
    private String id;

    @ApiModelProperty(value = "用户名", name = "username", example = "imoocuser", required = true)
    public String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    public String password;

    @ApiModelProperty(hidden = true)
    @Column(name = "face_image")
    private String faceImage;

    @ApiModelProperty(hidden = true)
    private String nickname;

    @ApiModelProperty(hidden = true)
    @Column(name = "fans_counts")
    private Integer fansCounts;

    @ApiModelProperty(hidden = true)
    @Column(name = "follow_counts")
    private Integer followCounts;

    @ApiModelProperty(hidden = true)
    @Column(name = "receive_like_counts")
    private Integer receiveLikeCounts;
}