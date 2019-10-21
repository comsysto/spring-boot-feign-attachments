package com.comsysto.springboot.feignattachmentsserver.domain

data class RobotConstructionPlan(
        val name: String,
        val contentType: String?,
        val plan: ByteArray,
        val description: String?
)
