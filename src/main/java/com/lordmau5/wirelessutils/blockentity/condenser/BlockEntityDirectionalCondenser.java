package com.lordmau5.wirelessutils.blockentity.condenser;

import com.lordmau5.wirelessutils.blockentity.BlockEntityDirectionalMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDirectionalCondenser extends BlockEntityDirectionalMachineBase {
    public BlockEntityDirectionalCondenser(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.directional_condenser.get(), pPos, pBlockState);
    }
}
