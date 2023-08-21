package com.lordmau5.wirelessutils.blockentity.condenser;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPositionalCondenser extends BlockEntityMachineBase {
    public BlockEntityPositionalCondenser(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.positional_condenser.get(), pPos, pBlockState);
    }
}
