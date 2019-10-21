package com.comsysto.springboot.feignattachmentsclient.clients

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.multipart.MultipartFile

@FeignClient(name = "robot-service-client", url = "http://localhost:8080", path = "/robots")
interface RobotFeignClient {

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addRobot(@RequestBody robot: RobotModel)

    @GetMapping(path = ["/{name}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getRobot(@PathVariable name: String) : RobotModel

    @GetMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getRobots() : List<RobotModel>

    @PostMapping(path = ["/{robotName}/constructionplan"], produces = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun addConstructionPlan(
            @PathVariable robotName: String,
            @PathVariable(name = "description") description: String,
            @PathVariable(name = "attachment") document: MultipartFile)

    @GetMapping(path = ["/robotName/constructionplan"])
    fun getConstructionPlan(@PathVariable robotName: String) : Any
}
