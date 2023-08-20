package com.lordmau5.wirelessutils.lib;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

public enum DirectionRotatable implements StringRepresentable {
    DOWN_Z(Direction.DOWN, false, "down_z", 7),
    UP_Z(Direction.UP, false, "up_z", 6),
    NORTH(Direction.NORTH, false, "north", 3),
    SOUTH(Direction.SOUTH, false, "south", 2),
    WEST(Direction.WEST, false, "west", 5),
    EAST(Direction.EAST, false, "east", 4),
    DOWN_X(Direction.DOWN, true, "down_x", 1),
    UP_X(Direction.UP, true, "up_x", 0);

    public final Direction direction;
    public final boolean rotation_x;
    public final String name;
    private final int opposite;

    DirectionRotatable(Direction direction, boolean rotation_x, String name, int opposite) {
        this.direction = direction;
        this.rotation_x = rotation_x;
        this.name = name;
        this.opposite = opposite;
    }

    public static DirectionRotatable fromFacing(Direction facing, boolean rotation_x) {
        return values()[facing.ordinal() + (facing.getAxis() == Direction.Axis.Y && rotation_x ? 6 : 0)];
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
            case UP_X -> UP_Z;
            case UP_Z -> UP_X;
            case DOWN_X -> DOWN_X;
            case DOWN_Z -> DOWN_Z;
        };
    }

    public DirectionRotatable rotateX() {
        return switch (this) {
            case NORTH -> DOWN_X;
            default -> throw new IllegalStateException("Unable to get X-rotated facing of " + this);
            case SOUTH -> UP_X;
            case UP_X, UP_Z -> NORTH;
            case DOWN_X, DOWN_Z -> SOUTH;
        };
    }

    public DirectionRotatable rotateZ() {
        return switch (this) {
            case EAST -> DOWN_Z;
            default -> throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
            case WEST -> UP_Z;
            case UP_X, UP_Z -> EAST;
            case DOWN_Z, DOWN_X -> WEST;
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
