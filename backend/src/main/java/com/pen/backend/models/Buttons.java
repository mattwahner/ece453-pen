package com.pen.backend.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Used to hold data about pen buttons")
public class Buttons {

    @NotNull
    private Boolean leftPressed;

    @NotNull
    private Boolean middlePressed;

    @NotNull
    @ApiModelProperty(notes = "Assert to true when the middle button is double pressed")
    private Boolean middleDoublePressed;

    @NotNull
    private Boolean rightPressed;

    public Boolean getLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(Boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public Boolean getMiddlePressed() {
        return middlePressed;
    }

    public void setMiddlePressed(Boolean middlePressed) {
        this.middlePressed = middlePressed;
    }

    public Boolean getMiddleDoublePressed() {
        return middleDoublePressed;
    }

    public void setMiddleDoublePressed(Boolean middleDoublePressed) {
        this.middleDoublePressed = middleDoublePressed;
    }

    public Boolean getRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(Boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
}
