package com.comsysto.springboot.feignattachmentsclient.web

import com.comsysto.springboot.feignattachmentsclient.clients.RobotModel
import com.comsysto.springboot.feignattachmentsclient.service.RobotClientService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/client/robots"], produces = [MediaType.APPLICATION_JSON_VALUE])
class RobotClientController(internal val robotClientService: RobotClientService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRobots() {
        robotClientService.createRobots()
    }

    @GetMapping
    fun getRobots() : Collection<RobotModel> = robotClientService.getRobots()

    @PostMapping(path = ["/{robotName}/constructionplan"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createConstructionDocumentation(@PathVariable robotName: String) = robotClientService.addConstructionPlan(robotName)
}
