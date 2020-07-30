package com.lieeber.imoocvideo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice


@PropertySource(value = ["classpath:config/my_configuration2.properties"])
@ConfigurationProperties(prefix = "li")
@Component
class MyConfiguration {
    lateinit var firstname:String
    lateinit var lastname:String
    var age:Int = 0

    val code: HashMap<Int, String> = HashMap()
    override fun toString(): String {
        return "MyConfiguration(firstname='$firstname', lastname='$lastname', age=$age, code=$code)"
    }


}