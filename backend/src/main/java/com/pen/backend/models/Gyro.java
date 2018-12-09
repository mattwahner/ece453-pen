package com.pen.backend.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Holds gyro data")
public class Gyro {

    private static final String GYRO_MESSAGE = "Gyro data should be an integer between 0 and 359";

    @NotNull
    @Min(0)
    @Max(359)
    @ApiModelProperty(notes = GYRO_MESSAGE)
    private Integer x;

    @NotNull
    @Min(0)
    @Max(359)
    @ApiModelProperty(notes = GYRO_MESSAGE)
    private Integer y;

    @NotNull
    @Min(0)
    @Max(359)
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
