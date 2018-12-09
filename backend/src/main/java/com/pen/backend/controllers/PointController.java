package com.pen.backend.controllers;

import com.pen.backend.models.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public PointController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/point")
    public void addPoints(@Validated @RequestBody Point point) {
        simpMessagingTemplate.convertAndSend("/topic/point", point);
    }

}
