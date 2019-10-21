package com.comsysto.springboot.feignattachmentsserver.web

import com.comsysto.springboot.feignattachmentsserver.domain.Robot
import com.comsysto.springboot.feignattachmentsserver.domain.RobotConstructionPlan
import com.comsysto.springboot.feignattachmentsserver.service.RobotService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(path = ["/robots"], produces = [MediaType.APPLICATION_JSON_VALUE])
class RobotController(internal val robotService: RobotService) {
    @GetMapping
    fun getRobots() : Collection<Robot> = robotService.getRobots()

    @GetMapping(path = ["/{name}"])
    fun getRobot(@PathVariable name: String) = robotService.getRobot(name)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addRobot(@RequestBody robot: Robot) : ResponseEntity<Nothing> {
        robotService.addRobot(robot)
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{name}")
                        .buildAndExpand(robot.name)
                        .toUri())
                .build()
    }

    @GetMapping(path = ["/{robotName}/constructionplan"])
    fun getConstructionPlan(@PathVariable robotName: String)=
            robotService.getConstructionPlan(robotName)?.let {
                robotConstructionPlan ->
                ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, robotConstructionPlan.contentType)
                        .body(robotConstructionPlan.plan)
            }

    @PostMapping(path = ["/{robotName}/constructionplan"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun addConstructionPlan(
            @PathVariable robotName: String,
            @RequestPart("description", required = false) description: String?,
            @RequestPart("attachment") document: MultipartFile): ResponseEntity<Nothing> {
        robotService.addConstructionPlan(
                robotName,
                RobotConstructionPlan(document.originalFilename!!, document.contentType, document.bytes, description))
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .buildAndExpand(robotName)
                        .toUri())
                .build()
    }
}
