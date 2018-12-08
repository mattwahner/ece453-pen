package com.pen.backend.controllers;

import com.pen.backend.models.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointsController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public PointsController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/points")
    public void addPoints(@Validated @RequestBody Points points) {
        simpMessagingTemplate.convertAndSend("/topic/points", points);
    }

}
