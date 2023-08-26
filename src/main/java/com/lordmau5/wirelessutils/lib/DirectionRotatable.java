package com.lordmau5.wirelessutils.lib;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

public enum DirectionRotatable implements StringRepresentable {
    DOWN(Direction.DOWN, "down", 1),
    UP(Direction.UP, "up", 0),
    NORTH(Direction.NORTH, "north", 3),
    SOUTH(Direction.SOUTH, "south", 2),
    WEST(Direction.WEST, "west", 5),
    EAST(Direction.EAST, "east", 4);

    public final Direction direction;

    public final String name;
    private final int opposite;

    DirectionRotatable(Direction direction, String name, int opposite) {
        this.direction = direction;
        this.name = name;
        this.opposite = opposite;
    }

    public static DirectionRotatable fromDirection(Direction facing) {
        return values()[facing.ordinal()];
    }

    public String toString() {
        return name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public DirectionRotatable getOpposite() {
        return DirectionRotatable.values()[opposite];
    }

    public DirectionRotatable rotateY() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case UP -> UP;
            case DOWN -> DOWN;
        };
    }

    public DirectionRotatable rotateX() {
        return switch (this) {
            case NORTH -> DOWN;
            default -> throw new IllegalStateException("Unable to get X-rotated facing of " + this);
            case SOUTH -> UP;
            case UP -> NORTH;
            case DOWN -> SOUTH;
        };
    }

    public DirectionRotatable rotateZ() {
        return switch (this) {
            case EAST -> DOWN;
            default -> throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
            case WEST -> UP;
            case UP -> EAST;
            case DOWN -> WEST;
        };
    }

    public DirectionRotatable rotateAround(Direction.Axis axis) {
        switch (axis) {
            case X -> {
                if (this != EAST && this != WEST)
                    return rotateX();
                return this;
            }
            case Y -> {
                return rotateY();
            }
            case Z -> {
                if (this != NORTH && this != SOUTH)
                    return rotateZ();
                return this;
            }
            default -> throw new IllegalStateException("Unable to get facing for axis: " + axis);
        }
    }
}
