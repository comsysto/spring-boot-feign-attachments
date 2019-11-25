package com.comsysto.springboot.feignattachmentsclient.service

import com.comsysto.springboot.feignattachmentsclient.clients.RobotFeignClient
import com.comsysto.springboot.feignattachmentsclient.clients.RobotModel
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import org.springframework.util.ResourceUtils
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream

@Service
class RobotClientService(internal val robotClient: RobotFeignClient) {
    private val robotNames = mutableListOf("R2D2", "C3PO", "BB-8", "Hover")
    private val robotConstructionPlans = mutableMapOf(
            Pair("R2D2", "r2d2-construction-plan.txt"),
            Pair("C3PO", "c3po-construction-plan.txt")
    )

    fun createRobots() {
        robotNames.forEach { robotName: String ->
            robotClient.addRobot(RobotModel(robotName, "HOVER"))
        }
    }

    fun getRobots(): List<RobotModel> = robotClient.getRobots()

    fun addConstructionPlan(robotName: String) {
        require(robotConstructionPlans.containsKey(robotName)) { "No construction plan exists for robot $robotName" }

        val fileName = robotConstructionPlans[robotName]
        robotClient.addConstructionPlan(
                robotName,
                "Added by Robot client",
                InMemoryMultipartFile(
                        fileName!!,
                        ResourceUtils.getFile("classpath:$fileName").readBytes()
                )
        )
    }
}

class InMemoryMultipartFile(
        private val name: String,
        private val content: ByteArray
) : MultipartFile {
    override fun getName(): String {
        return name
    }

    override fun isEmpty(): Boolean {
        return content.isEmpty()
    }

    override fun getSize(): Long {
        return content.size.toLong()
    }

    override fun getBytes(): ByteArray {
        return content
    }

    override fun getOriginalFilename(): String? {
        return name
    }

    override fun getInputStream(): InputStream {
        return ByteArrayInputStream(content)
    }

    override fun getContentType(): String? {
        return "application/octet-stream"
    }

    override fun transferTo(dest: File) {
        FileCopyUtils.copy(content, dest)
    }
}
