package com.lieeber.imoocvideo.controller;

import org.springframework.stereotype.Component;

@Component
public class Demo2 implements MyInterface {
    @Override
    public void onPrint() {
        System.out.println("打印demo2");
    }
}
