package com.comsysto.springboot.feignattachmentsserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeignAttachmentsServerApplication

fun main(args: Array<String>) {
	runApplication<FeignAttachmentsServerApplication>(*args)
}
