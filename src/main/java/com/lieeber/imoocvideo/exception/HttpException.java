package com.lieeber.imoocvideo.exception;

public class HttpException extends RuntimeException {
    public int code;
    public int httpStatusCode = 500;
}
