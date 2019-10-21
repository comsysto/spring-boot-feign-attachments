package com.comsysto.springboot.feignattachmentsserver.domain

data class Robot(
        val name: String,
        val type: RobotType
)

enum class RobotType {
    HOVER,
    MOWER
}
