package com.comsysto.springboot.feignattachmentsclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FeignAttachmentsClientApplication

fun main(args: Array<String>) {
	runApplication<FeignAttachmentsClientApplication>(*args)
}
