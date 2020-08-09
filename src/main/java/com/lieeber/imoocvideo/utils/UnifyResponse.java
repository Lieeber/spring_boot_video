package com.lieeber.imoocvideo.utils;

import lombok.*;

/**
 * @Description: 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnifyResponse {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;


    public static UnifyResponse build(Integer status, String msg, Object data) {
        return new UnifyResponse(status, msg, data);
    }

    public static UnifyResponse ok(Object data) {
        return new UnifyResponse(data);
    }

    public static UnifyResponse ok() {
        return new UnifyResponse(null);
    }

    public static UnifyResponse errorMsg(String msg) {
        return new UnifyResponse(500, msg, null);
    }

    public static UnifyResponse errorMap(Object data) {
        return new UnifyResponse(501, "error", data);
    }

    public static UnifyResponse errorTokenMsg(String msg) {
        return new UnifyResponse(502, msg, null);
    }

    public static UnifyResponse errorException(String msg) {
        return new UnifyResponse(555, msg, null);
    }

    public UnifyResponse(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.code == 200;
    }
}
