package com.pen.backend.controllers;

import com.pen.backend.models.Points;
import com.pen.backend.singletons.PointHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointsController {

    @GetMapping("/points")
    public Points getPoints() {
        return PointHolder.points;
    }

    @PostMapping("/points")
    public void addPoints(@Validated @RequestBody Points points) {
        PointHolder.points.getPoints().addAll(points.getPoints());
    }

    @PostMapping("/clear")
    public void clearPoints() {
        PointHolder.points.getPoints().clear();
    }

}
