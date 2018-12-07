package com.pen.backend.controllers;

import com.pen.backend.models.Points;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointsController {

    @PostMapping("/points")
    @SendTo("/topic/points")
    public Points addPoints(@Validated @RequestBody Points points) {
        return points;
    }

}
