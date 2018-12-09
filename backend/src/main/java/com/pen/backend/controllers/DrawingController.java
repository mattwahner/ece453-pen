package com.pen.backend.controllers;

import com.pen.backend.models.Drawing;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(
            value = "Returns values used by pen to update IO",
            notes = "First line: red byte, Second line: green byte, Third line: blue byte, Fourth line: thickness (0-5)"
    )
    public String getDrawing() {
        return this.drawing.getRed() + "\n" + this.drawing.getGreen() + "\n" + this.drawing.getBlue() + "\n"
                + this.drawing.getThickness();
    }

}
