package com.lordmau5.wirelessutils.lib.block;

import net.minecraft.core.Direction;

public interface IFacingBlock {
    Direction getFacing();

    default Direction getSideBasedOnFacing(Direction side) {
        return SidedIO.OUTPUT_TABLE[getFacing().ordinal()][side.ordinal()];
    }

    default SidedIO.SidedIOFace getIOBasedOnFacing(Direction side) {
        return SidedIO.SidedIOFace.values()[getSideBasedOnFacing(side).ordinal()];
    }
}
