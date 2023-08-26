package com.lordmau5.wirelessutils.lib.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;

public interface IFacingBlock {
    Direction getFacing();

    default Rotation getRotation() {
        return Rotation.NONE;
    }

    default Direction getSideBasedOnFacing(Direction side) {
        return SidedIO.getDirectionTable()[getFacing().ordinal()][side.ordinal()];
    }

    default boolean canRotateAroundAxis(Direction.Axis axis) {
        return true;
    }
}
