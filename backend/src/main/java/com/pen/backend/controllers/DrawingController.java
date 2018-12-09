package com.pen.backend.controllers;

import com.pen.backend.models.Drawing;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrawingController {

    private Drawing drawing = new Drawing();

    @MessageMapping("/drawing")
    public void setDrawing(@Payload Drawing drawing) {
        this.drawing = drawing;
    }

    @GetMapping("/drawing")
    public Drawing getDrawing() {
        return this.drawing;
    }

}
