package com.lordmau5.wirelessutils.blockentity;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDirectionalMachineBase extends BlockEntityMachineBase {
    public BlockEntityDirectionalMachineBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    public boolean isDirectionValidForState(Direction dir) {
        BlockState state = getBlockState();
        DirectionRotatable facing = state.getValue(BlockMachineBase.FACING);
        if (dir == facing.direction) return false;

        return super.isDirectionValidForState(dir);
    }
}
