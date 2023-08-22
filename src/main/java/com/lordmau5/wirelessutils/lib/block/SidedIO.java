package com.lordmau5.wirelessutils.lib.block;

import net.minecraft.core.Direction;

import java.util.HashMap;

public class SidedIO {
    public enum SidedIOStates {
        NEUTRAL, // Allow input and output at will
        DISABLED, // Don't allow any input or output
        INPUT, // Only allow input
        OUTPUT // Only allow output
    }

    private final HashMap<Direction, SidedIOStates> ioStates = new HashMap<>();

    public void setState(Direction direction, SidedIOStates state) {
        ioStates.put(direction, state);
    }

    public SidedIOStates getState(Direction direction) {
        return ioStates.getOrDefault(direction, SidedIOStates.NEUTRAL);
    }
}
