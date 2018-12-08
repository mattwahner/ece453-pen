package com.pen.backend.controllers;

import com.pen.backend.models.Window;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WindowController {

    private Window window;

    @MessageMapping("/window")
    public void setWindow(@Payload Window window) {
        this.window = window;
        System.out.println("Width: " + window.getWidth() + "\tHeight: " + window.getHeight());
    }

    @GetMapping("/window")
    public Window getWindow() {
        return window;
    }

}
