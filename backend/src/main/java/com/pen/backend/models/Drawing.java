package com.pen.backend.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Holds data used for pen to display current state of the website")
public class Drawing {

    private static final String RGB_NOTE = "Byte used for RGB led to show current color on pen";

    @ApiModelProperty(notes = "Value between 0 and 5 for showing current thickness on pen")
    private int thickness;

    @ApiModelProperty(notes = RGB_NOTE)
    private int red;

    @ApiModelProperty(notes = RGB_NOTE)
    private int green;

    @ApiModelProperty(notes = RGB_NOTE)
    private int blue;

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
