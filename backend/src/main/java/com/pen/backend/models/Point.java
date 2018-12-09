package com.pen.backend.models;

import javax.validation.constraints.NotNull;

public class Point {

    @NotNull
    private Integer x;

    @NotNull
    private Integer y;

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
}
