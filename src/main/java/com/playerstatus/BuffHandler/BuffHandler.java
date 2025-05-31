package com.playerstatus.BuffHandler;

import lombok.Getter;

public abstract class BuffHandler {
    protected boolean isActive;
    @Getter
    protected int ticksRemaining;

    public boolean isActive() {
        return isActive;
    }

    public boolean decreaseTime() {
        // decreases the number of ticks remaining on the spell
        // also returns if it is still active
        if (ticksRemaining > 0) {
            ticksRemaining--;
        } else {
            isActive = false;
        }
        return isActive;
    }

    public abstract void activate();

    public void deactivate() {
        ticksRemaining = 0;
        isActive = false;
    }
}
