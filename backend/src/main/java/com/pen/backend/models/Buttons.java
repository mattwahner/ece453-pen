package com.pen.backend.models;

import javax.validation.constraints.NotNull;

public class Buttons {

    @NotNull
    private Boolean leftPressed;

    @NotNull
    private Boolean middlePressed;

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

    public Boolean getRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(Boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
}
