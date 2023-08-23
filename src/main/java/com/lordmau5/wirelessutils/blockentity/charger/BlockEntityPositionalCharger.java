package com.lordmau5.wirelessutils.blockentity.charger;

import com.lordmau5.wirelessutils.blockentity.BlockEntityPositionalMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityPositionalCharger extends BlockEntityPositionalMachineBase {
    public BlockEntityPositionalCharger(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.positional_charger.get(), pPos, pBlockState);
    }
}
