package com.lordmau5.wirelessutils.lib.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;

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

        public String getName() {
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

        public String getName() {
            return name;
        }
    }

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

        for (SidedIOFace face : SidedIOFace.values()) {
            if (sidePredicate.test(face)) {
                filteredMap.put(face, getStates().get(face));
            } else {
                filteredMap.put(face, SidedIOState.NONE);
            }
        }

        return filteredMap;
    }

    public static Direction[][] getDirectionTable() {
        return new Direction[][]{
                // BOTTOM, TOP, FRONT, BACK, RIGHT, LEFT
                /* Facing DOWN */ {Direction.SOUTH, Direction.NORTH, Direction.DOWN, Direction.UP, Direction.EAST, Direction.WEST},
                /* Facing UP */ {Direction.NORTH, Direction.SOUTH, Direction.UP, Direction.DOWN, Direction.EAST, Direction.WEST},

                /* Facing NORTH */ {Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST},
                /* Facing SOUTH */ {Direction.DOWN, Direction.UP, Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST},

                /* Facing EAST */ {Direction.DOWN, Direction.UP, Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH},
                /* Facing WEST */ {Direction.DOWN, Direction.UP, Direction.WEST, Direction.EAST, Direction.SOUTH, Direction.NORTH},
        };
    }

    public static Direction[][] getRotationTable() {
        return new Direction[][]{
                // 0, 90, 180, 270
                /* Facing DOWN */ {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST},
                /* Facing UP */ {Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST},

                /* Facing NORTH */ {Direction.UP, Direction.EAST, Direction.DOWN, Direction.WEST},
                /* Facing SOUTH */ {Direction.UP, Direction.WEST, Direction.DOWN, Direction.EAST},

                /* Facing EAST */ {Direction.UP, Direction.NORTH, Direction.DOWN, Direction.SOUTH},
                /* Facing WEST */ {Direction.UP, Direction.SOUTH, Direction.DOWN, Direction.NORTH},
        };
    }

    public static Direction getSideBasedOnFacing(Direction facing, Direction side) {
        return getDirectionTable()[facing.ordinal()][side.ordinal()];
    }

    public static SidedIOFace getIOBasedOnFacing(Direction facing, Direction side) {
        return SidedIOFace.values()[getSideBasedOnFacing(facing, side).ordinal()];
    }

    public static SidedIOFace getSide(Direction facing, Rotation rotation, Direction absoluteSide) {
        // Rotation, then Direction Ordinal
        SidedIOFace[][][] lookupTable = new SidedIOFace[][][] {
                // ZERO Rotation
                {
                        // Down
                        {
                                SidedIOFace.FRONT,
                                SidedIOFace.BACK,
                                SidedIOFace.TOP,
                                SidedIOFace.BOTTOM,
                                SidedIOFace.RIGHT,
                                SidedIOFace.LEFT
                        },
                        // Up
                        {
                                SidedIOFace.BACK,
                                SidedIOFace.FRONT,
                                SidedIOFace.TOP,
                                SidedIOFace.BOTTOM,
                                SidedIOFace.RIGHT,
                                SidedIOFace.LEFT
                        },
                        // North
                        {
                                SidedIOFace.BOTTOM,
                                SidedIOFace.TOP,
                                SidedIOFace.FRONT,
                                SidedIOFace.BACK,
                                SidedIOFace.RIGHT,
                                SidedIOFace.LEFT
                        },
                        // South
                        {
                                SidedIOFace.BOTTOM,
                                SidedIOFace.TOP,
                                SidedIOFace.BACK,
                                SidedIOFace.FRONT,
                                SidedIOFace.LEFT,
                                SidedIOFace.RIGHT
                        },
                        // West
                        {
                                SidedIOFace.BOTTOM,
                                SidedIOFace.TOP,
                                SidedIOFace.LEFT,
                                SidedIOFace.RIGHT,
                                SidedIOFace.FRONT,
                                SidedIOFace.BACK
                        },
                        // East
                        {
                                SidedIOFace.BOTTOM,
                                SidedIOFace.TOP,
                                SidedIOFace.RIGHT,
                                SidedIOFace.LEFT,
                                SidedIOFace.BACK,
                                SidedIOFace.FRONT
                        },
                },
        };

        return lookupTable[rotation.ordinal()][facing.ordinal()][absoluteSide.ordinal()];
    }
}
