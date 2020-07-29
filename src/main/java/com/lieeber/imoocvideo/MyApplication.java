package com.lieeber.imoocvideo;

import com.lieeber.imoocvideo.controller.Demo2;
import com.lieeber.imoocvideo.controller.HelloController;
import com.lieeber.imoocvideo.controller.MyInterface;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//@EnableAutoConfiguration
//@ComponentScan
@Import(HelloController.class)
public class MyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(MyApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        MyInterface myInterface = (MyInterface) context.getBean("demo2");
        myInterface.onPrint();

    }
}
