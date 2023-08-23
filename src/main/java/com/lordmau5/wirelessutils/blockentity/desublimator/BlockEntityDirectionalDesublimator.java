package com.lordmau5.wirelessutils.blockentity.desublimator;

import com.lordmau5.wirelessutils.blockentity.BlockEntityDirectionalMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDirectionalDesublimator extends BlockEntityDirectionalMachineBase {
    public BlockEntityDirectionalDesublimator(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.directional_desublimator.get(), pPos, pBlockState);
    }
}
