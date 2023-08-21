package com.lordmau5.wirelessutils.blockentity.charger;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPositionalCharger extends BlockEntityMachineBase {
    public BlockEntityPositionalCharger(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.positional_charger.get(), pPos, pBlockState);
    }
}
