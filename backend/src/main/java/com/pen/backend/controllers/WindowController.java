package com.pen.backend.controllers;

import com.pen.backend.models.Window;
import io.swagger.annotations.ApiOperation;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WindowController {

    private Window window = new Window();

    @MessageMapping("/window")
    public void setWindow(@Payload Window window) {
        this.window = window;
    }

    @GetMapping("/window")
    @ApiOperation(
            value = "Returns current screen size",
            notes = "First line: width in pixels, Second line: height in pixels"
    )
    public String getWindow() {
        return this.window.getWidth() + "\n" + this.window.getHeight();
    }

}
