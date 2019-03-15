package com.neueda.shortener.web;

import com.neueda.shortener.model.Robot;
import com.neueda.shortener.service.RobotSimulationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RobotController API Entry point
 *
 * @author Mike Adamenko (mnadamenko@gmail.com)
 */
@RestController
@RequestMapping("/api")
class RobotController {

    private final Logger log = LoggerFactory.getLogger(RobotController.class);
    @Autowired
    RobotSimulationService robotSimulationService;

    @PostMapping(value = "/calculateNewPosition", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    ResponseEntity<Robot> calculateNewPosition(@RequestBody String script) {
        log.info("Request to calculate new robot position: {}");
        return ResponseEntity.ok().body(robotSimulationService.process(script));
    }

}
