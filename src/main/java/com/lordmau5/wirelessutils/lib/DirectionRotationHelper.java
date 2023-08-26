package com.lordmau5.wirelessutils.lib;

import com.lordmau5.wirelessutils.lib.block.SidedIO.SidedIOFace;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;

public class DirectionRotationHelper {
    public static SidedIOFace getSide(Direction facing, Rotation rotation, Direction absoluteSide) {
        SidedIOFace[] sided_table = new SidedIOFace[] { SidedIOFace.TOP, SidedIOFace.RIGHT, SidedIOFace.BOTTOM, SidedIOFace.LEFT };

        int ordRotDirIndex = getOrdinalRotationDirectionIndex(facing, rotation, absoluteSide);

        if (facing == absoluteSide) {
            return SidedIOFace.FRONT;
        }
        else if (facing.getOpposite() == absoluteSide) {
            return SidedIOFace.BACK;
        }
        else {
            return sided_table[ordRotDirIndex];
        }
    }

    private static int getOrdinalRotationDirectionIndex(Direction facing, Rotation rotation, Direction absoluteSide) {
        Direction[][] lookup_table = new Direction[][] {
                /* DOWN, based on north */ {Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST},
                /* UP, based on north */ {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST},
                /* NORTH */ {Direction.UP, Direction.WEST, Direction.DOWN, Direction.EAST},
                /* SOUTH */ {Direction.UP, Direction.EAST, Direction.DOWN, Direction.WEST},
                /* WEST */ {Direction.UP, Direction.SOUTH, Direction.DOWN, Direction.NORTH},
                /* EAST */ {Direction.UP, Direction.NORTH, Direction.DOWN, Direction.SOUTH},
        };

        Direction[] inner_table = lookup_table[facing.ordinal()];
        for (int i = 0; i < inner_table.length; i++) {
            if (inner_table[(i + rotation.ordinal()) % inner_table.length] == absoluteSide)
                return i;
        }

        return 0;
    }

    public static Rotation getRotationBasedOnPreviousAndNewFacing(Direction oldFacing, Direction newFacing, Rotation rotation) {
        Direction.Axis oldAxis = oldFacing.getAxis();
        Direction.Axis newAxis = newFacing.getAxis();

        if (oldAxis == newAxis) {
            rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
        }
        else if (oldAxis == Direction.Axis.Y || newAxis == Direction.Axis.Y) {
            switch (oldAxis) {
                case X -> rotation = rotation.getRotated(oldFacing == Direction.EAST ? Rotation.COUNTERCLOCKWISE_90 : Rotation.CLOCKWISE_90);
                case Y -> {
                    switch (newAxis) {
                        case X -> rotation = rotation.getRotated(oldFacing == Direction.DOWN ? Rotation.COUNTERCLOCKWISE_90: Rotation.CLOCKWISE_90);
                        case Z -> rotation = rotation.getRotated(Rotation.CLOCKWISE_180);
                        default -> {
                        }
                    }
                }
                default -> {
                }
            }
        }

        return rotation;
    }

    public static Rotation getRotationBasedOnYFacing(Direction placedDirection, float yRotation) {
        Rotation[] lookup_table = new Rotation[] { Rotation.NONE, Rotation.CLOCKWISE_180, Rotation.COUNTERCLOCKWISE_90, Rotation.CLOCKWISE_90 };
        Direction playerFacing = Direction.fromYRot(yRotation);

        Rotation rotation = Rotation.NONE;

        switch(placedDirection) {
            case DOWN -> {
                rotation = lookup_table[playerFacing.ordinal() - 2];
                if (playerFacing.getAxis() == Direction.Axis.Z)
                    rotation = rotation.getRotated(Rotation.CLOCKWISE_180);
            }
            case UP -> {
                rotation = lookup_table[playerFacing.ordinal() - 2];
            }
            default -> {
            }
        }

        return rotation;
    }
}
