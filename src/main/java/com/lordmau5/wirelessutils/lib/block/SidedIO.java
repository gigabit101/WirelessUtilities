package com.lordmau5.wirelessutils.lib.block;

import net.minecraft.core.Direction;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

public class SidedIO {
    public enum SidedIOState {
        NONE, // Allow input and output at will
        INPUT, // Only allow input
        OUTPUT, // Only allow output
        DISABLED; // Don't allow any input or output

        private final String name = toString().toLowerCase(Locale.ROOT);

        public String getName()
        {
            return name;
        }
    }

    public enum SidedIOFace {
        BOTTOM,
        TOP,
        FRONT,
        BACK,
        RIGHT,
        LEFT;

        private final String name = toString().toLowerCase(Locale.ROOT);

        public String getName()
        {
            return name;
        }
    }

    public static final Direction[][] OUTPUT_TABLE = {
            // BOTTOM, TOP, FRONT, BACK, RIGHT, LEFT
            /* Facing DOWN */ {Direction.NORTH, Direction.SOUTH, Direction.DOWN, Direction.UP, Direction.EAST, Direction.WEST},
            /* Facing UP */ {Direction.SOUTH, Direction.NORTH, Direction.UP, Direction.DOWN, Direction.WEST, Direction.EAST},
            /* Facing NORTH */ {Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST},
            /* Facing SOUTH */ {Direction.DOWN, Direction.UP, Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST},
            /* Facing EAST */ {Direction.DOWN, Direction.UP, Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH},
            /* Facing WEST */ {Direction.DOWN, Direction.UP, Direction.WEST, Direction.EAST, Direction.SOUTH, Direction.NORTH},
    };

    private final HashMap<SidedIOFace, SidedIOState> ioStates = new HashMap<>();

    public void setState(SidedIOFace face, SidedIOState state) {
        getStates().put(face, state);
    }

    public SidedIOState getState(SidedIOFace face) {
        return getStates().getOrDefault(face, SidedIOState.NONE);
    }

    public Map<SidedIOFace, SidedIOState> getStates() {
        return ioStates;
    }

    public Map<SidedIOFace, SidedIOState> getStates(Predicate<SidedIOFace> sidePredicate) {
        HashMap<SidedIOFace, SidedIOState> filteredMap = new HashMap<>();

        for(SidedIOFace face : SidedIOFace.values()) {
            if (sidePredicate.test(face)) {
                filteredMap.put(face, getStates().get(face));
            }
            else {
                filteredMap.put(face, SidedIOState.NONE);
            }
        }

        return filteredMap;
    }

    public static Direction getSideBasedOnFacing(Direction facing, Direction side) {
        Direction[][] OUTPUT_TABLE = {
                // BOTTOM, TOP, FRONT, BACK, RIGHT, LEFT
                /* Facing DOWN */ {Direction.NORTH, Direction.SOUTH, Direction.DOWN, Direction.UP, Direction.EAST, Direction.WEST},
                /* Facing UP */ {Direction.SOUTH, Direction.NORTH, Direction.UP, Direction.DOWN, Direction.WEST, Direction.EAST},
                /* Facing NORTH */ {Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST},
                /* Facing SOUTH */ {Direction.DOWN, Direction.UP, Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST},
                /* Facing EAST */ {Direction.DOWN, Direction.UP, Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH},
                /* Facing WEST */ {Direction.DOWN, Direction.UP, Direction.WEST, Direction.EAST, Direction.SOUTH, Direction.NORTH},
        };

        return OUTPUT_TABLE[facing.ordinal()][side.ordinal()];
    }

    public static SidedIO.SidedIOFace getIOBasedOnFacing(Direction facing, Direction side) {
        return SidedIO.SidedIOFace.values()[getSideBasedOnFacing(facing, side).ordinal()];
    }
}
