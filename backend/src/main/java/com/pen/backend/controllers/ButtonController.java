package com.pen.backend.controllers;

import com.pen.backend.models.Buttons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ButtonController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ButtonController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/buttons")
    public void setButtons(@Validated @RequestBody Buttons buttons) {
        simpMessagingTemplate.convertAndSend("/topic/buttons", buttons);
    }

}
