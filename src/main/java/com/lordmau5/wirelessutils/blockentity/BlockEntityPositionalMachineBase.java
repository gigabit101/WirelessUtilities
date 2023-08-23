package com.lordmau5.wirelessutils.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPositionalMachineBase extends BlockEntityMachineBase {
    public BlockEntityPositionalMachineBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    public boolean isSideValid(Direction side) {
        return side != getFacing() && side != Direction.UP && super.isSideValid(side);
    }
}
