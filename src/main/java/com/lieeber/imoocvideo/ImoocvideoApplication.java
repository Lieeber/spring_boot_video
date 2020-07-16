package com.lieeber.imoocvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.lieeber.imoocvideo.mapper")
@ComponentScan(basePackages = {"com.lieeber.imoocvideo","org.n3r.idworker"})
@SpringBootApplication
public class ImoocvideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImoocvideoApplication.class, args);
    }

}
