package com.comsysto.springboot.feignattachmentsclient.clients

import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients
class FeignConfig {

    @Bean
    fun clientFeignEncoder(messageConverters: ObjectFactory<HttpMessageConverters?>?): Encoder? {
        return SpringFormEncoder(SpringEncoder(messageConverters))
    }
}
