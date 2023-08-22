package com.lordmau5.wirelessutils.blockentity.desublimator;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.blockentity.BlockEntityPositionalMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPositionalDesublimator extends BlockEntityPositionalMachineBase {
    public BlockEntityPositionalDesublimator(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.positional_desublimator.get(), pPos, pBlockState);
    }
}
