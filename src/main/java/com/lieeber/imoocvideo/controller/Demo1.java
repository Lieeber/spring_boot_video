package com.lieeber.imoocvideo.controller;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

//@Component
//@ConditionalOnBean(name = "demo2")
public class Demo1 implements MyInterface {
    @Override
    public void onPrint() {
        System.out.println("打印demo1");
    }
}