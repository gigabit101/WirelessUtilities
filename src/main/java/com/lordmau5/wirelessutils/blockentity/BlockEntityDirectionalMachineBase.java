package com.lordmau5.wirelessutils.blockentity;

import com.lordmau5.wirelessutils.lib.block.SidedIO;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDirectionalMachineBase extends BlockEntityMachineBase {
    public BlockEntityDirectionalMachineBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    public boolean isSideValid(SidedIO.SidedIOFace face) {
        return face != SidedIO.SidedIOFace.FRONT && super.isSideValid(face);
    }
}
