package com.lieeber.imoocvideo.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource(value = ["classpath:config/my_configuration.properties"])
class FirstConfiguration {
    @Value("\${li.firstname}")
    lateinit var demo:String
}