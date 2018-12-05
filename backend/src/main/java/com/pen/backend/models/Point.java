package com.pen.backend.models;

import javax.validation.constraints.NotNull;

public class Point {

    @NotNull
    private Integer x;

    @NotNull
    private Integer y;

    @NotNull
    private Integer thickness;

    @NotNull
    private Integer red;

    @NotNull
    private Integer green;

    @NotNull
    private Integer blue;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public Integer getGreen() {
        return green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }
}
