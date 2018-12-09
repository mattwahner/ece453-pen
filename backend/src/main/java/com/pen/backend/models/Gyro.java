package com.pen.backend.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Holds gyro data")
public class Gyro {

    private static final String GYRO_MESSAGE = "Gyro data should be an integer between 0 and 359";

    @NotNull
    @Size(min = 0, max = 359, message = GYRO_MESSAGE)
    @ApiModelProperty(notes = GYRO_MESSAGE)
    private Integer x;

    @NotNull
    @Size(min = 0, max = 359, message = GYRO_MESSAGE)
    @ApiModelProperty(notes = GYRO_MESSAGE)
    private Integer y;

    @NotNull
    @Size(min = 0, max = 359, message = GYRO_MESSAGE)
    @ApiModelProperty(notes = GYRO_MESSAGE)
    private Integer z;

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

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
