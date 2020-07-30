package com.lieeber.imoocvideo.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionAdvice {
    @ExceptionHandler(value = [Exception::class])
    fun handleException(req: HttpServletRequest, e: Exception) {
//        println(e.message)
    }
}