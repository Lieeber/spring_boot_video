package com.lieeber.imoocvideo.exception;


import com.lieeber.imoocvideo.utils.UnifyResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public UnifyResponse handleException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        return UnifyResponse.errorMsg(exception.getMessage());
    }

    @ExceptionHandler(value = HttpException.class)
    @ResponseBody
    public UnifyResponse handleHttpException(HttpServletRequest request, HttpException exception) {
        exception.printStackTrace();
        return UnifyResponse.errorMsg(exception.getMessage());
    }
}
