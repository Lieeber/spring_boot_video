package com.lieeber.imoocvideo.exception

import java.lang.RuntimeException

class HttpException() : RuntimeException() {

    private var code: String? = null
    private var httpStatusCode: String? = null

//    constructor(code: String) {
//        this.code = code
//    }
}