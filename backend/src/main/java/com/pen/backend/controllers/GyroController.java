package com.pen.backend.controllers;

import com.pen.backend.models.Gyro;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GyroController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public GyroController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/gyro")
    @ApiOperation(value = "Send gyro data to backend", notes = "Ensure all gyro data is an integer between 0 and 359")
    public void setGyroData(@Validated @RequestBody Gyro gyro) {
        this.simpMessagingTemplate.convertAndSend("/topics/gyro", gyro);
    }

}
